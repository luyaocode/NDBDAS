from base64 import encode
from locale import currency
from pickle import NONE
import binascii
# import serial.tools.list_ports
import logging
import os
import struct
import datetime
# import WriteLog
logging.basicConfig(format='%(asctime)s,%(msecs)d %(levelname)-8s [%(filename)s:%(lineno)d] %(message)s',
                    datefmt='%d-%m-%Y:%H:%M:%S')

logging.getLogger().setLevel(logging.WARNING)
logger = logging.getLogger()

u8_lit = struct.Struct('B')
s8_lit = struct.Struct('b')
u16_lit = struct.Struct('<H')
s16_lit = struct.Struct('<h')
u32_lit = struct.Struct('<I')
s32_lit = struct.Struct('<i')
double_lit = struct.Struct('<d')
float_lit = struct.Struct('<f')
u16_big = struct.Struct('>H')
s16_big = struct.Struct('>h')
u32_big = struct.Struct('>I')
s32_big = struct.Struct('>i')
double_big = struct.Struct('>d')
float_big = struct.Struct('>f')
support_dbg_out = 0
support_dbg_send_frame = 1

ST_IDLE = 0
ST_HEAD = 1
ST_FRAME = 2
START_MARK = 0x68

ERR_FRAME = 1
ERR_SUM = 2
ERR_NONE = 0

data_temp = [0x33,0x34,0x32,0x33,0x33,0x33,0x33] #【00 01 ff 00 00 00 00 】
addr = [0xA0,0xA1,0xA2,0xA3,0xA4,0xA5]
# 数据标识表
id_name_dir = {
    0x0201ff00: '电压数据块',
    0x0202ff00: '电流数据块',
    0x0203ff00: '瞬时总有功功率数据块',
    0x0204ff00: '瞬时总无功功率数据块',
    0x0205ff00: '瞬时总视在功率',
    0x0206ff00: '功率因数数据块',
    0x02800001: '零线电流',
    0x0001ff00: '正向有功总电能数据块',
    0x0002ff00: '反向有功总电能',
    0x0003ff00: '组合无工1总电能',
    0x0004ff00: '组合无工2总电能',
    0x01010000: '正向有功总最大需量及发生时间',
    0x040005ff: '运行状态字',
    0x02800002: '电网频率',
    0x02800004: '当前有功需量',
    0x0205ff00: '瞬时视在功率',
}


def out_send_frame(name, msg):
    if support_dbg_send_frame:
        print(name, msg)


def dis_wdbg(msg, color=u'白'):
    color_val = {u'红': '\033[1;31m', u'绿': '\033[1;33m', u'蓝': '\033[1;34m'} #颜色对应表，网上搜一下就有了
    if color in color_val.keys():
        os.system('')
        print(color_val[color] + str(msg) + '\033[0m')
    else:
        print(msg)


def get_sum(data):    #校验和
    sum_val: int = 0
    for val in data:
        sum_val += val
    return sum_val % 256


class dlt_645_2007:
    def __init__(self):
        self.cache = bytearray() #空字节序列，准备接收数据
        self.st = ST_IDLE
        self.idx = 0
        self.addr = bytearray()
        self.frame_size = 0
        self.data = []
        self.di = 0

    # def decode_645data_list(self, data):  # list循环减33操作 组帧传送
    #     val = [x - 0x33 for x in data]
    #     return val
    def decode_645data_list(self, data):  # list循环减33操作 组帧传送
        decode_data = []
        val = 0xff
        for i in data:
            if i > 0x32:
                val = i - 0x33
            decode_data.append(val)
        return decode_data


    def decode_645data_unit(self, data):  # 单个数据减33操作 组帧传送
        val = 0xff  # 因为0xff+0x33=0x132
        if data > 0x32:
            val = data - 0x33
        # val = [x + 0x33 for x in data]
        return val

    # def encode_645data_list(self, data):  # list循环加33操作 组帧传送
    #     val = [x + 0x33 for x in data]
    #     return val

    def encode_645data_list(self, data):  # list循环加33操作 组帧传送
        decode_data = []
        val = 32
        for i in data:
            if i < 0xff:
                val = i + 0x33
            decode_data.append(val)
        return decode_data


    def encode_645data_unit(self, data):  # 单个数据加33操作 组帧传送
        val = 32  # 因为0xff+0x33=0x132
        if data < 0xff:
            val = data + 0x33
        # val = [x + 0x33 for x in data]
        return val

    def decode_di(self):
        dot = bytearray(4)
        dot[0] = self.decode_645data_unit(self.cache[13])
        dot[1] = self.decode_645data_unit(self.cache[12])
        dot[2] = self.decode_645data_unit(self.cache[11])
        dot[3] = self.decode_645data_unit(self.cache[10])
        di = u32_lit.unpack_from(dot, 0)[0]  #int型 unpack_from(buffer, offset=0)  将打包的字节写入从offset偏移开始的可写缓冲buffer
        # di = dot[0] << 24 | dot[1] <<16 | dot[2] << 8 | dot[3] 
        return hex(di)
    
    def encode(self, 
               base_addr,
               ctrl,
               data):  #发送时用10进制数组发送     发给下位机的组帧
        msg = []
        msg.append(0x68)
        msg.extend(base_addr)
        msg.append(0x68)
        msg.append(ctrl)
        msg.append(len(data))
        send_data = self.encode_645data_list(data)  # 主给从发 数据依次加33H
        msg.extend(send_data)
        sum_val = get_sum(msg[0:])
        msg.append(sum_val)
        msg.append(0x16)
        return msg

    def decode(self, data): #解来自下位机的响应帧--要返回具体的响应数据才可以
        try:
            logger.info(binascii.hexlify(data))
            print("--------------------开始遍历数组，解析数据----------------------")
            for temp in data:                                        # data即为接收到的数据帧
                if self.st == ST_IDLE:                               # 开始接收标志，初始化的时候self.st == ST_IDLE，所以data帧的第一个数据会进入这个循环
                    if temp == START_MARK:                           # 看帧的第一个数据是否为0x68，如果是的话，存进数组，并且给数组下标idx=1，且让self.st = ST_HEAD,第二个数据就不进这个循环了
                        self.cache = bytearray(0)
                        self.cache.append(temp)
                        self.idx = 1
                        self.st = ST_HEAD
                elif self.st == ST_HEAD:                             # 从0x68开始接收，然后在这里循环写入十个数据，因为浮标没变 #（这十个数据是两个68 六个地址，一个控制码，一个数据长度）
                    self.cache.append(temp)                          # 第二个数据开始，一直存在cache这个数组中（循环10次），并且每存一次，数组下标加1
                    self.idx += 1
                    if self.idx == 10:                               # 数据的第10位是数据长度
                        if self.cache[7] != START_MARK:              # 如果第7位不是0x68，那么说明数据有问题，重新开始解析存储，数组下标从1开始
                            self.idx = 1                   
                            self.cache = bytearray(0)
                            self.cache.append(START_MARK)
                        else:
                            self.addr = bytearray(self.cache[1:7])   # 接收正确，提取属性
                            self.frame_size = self.cache[9] + 12     #12：十个固定数据加一个校验码、一个帧尾     总帧长度=固定数据位长度12+数据长度
                            self.st = ST_FRAME             
                else:                                                # 继续接收len(data)后边的所有数据
                    self.cache.append(temp)
                    self.idx += 1
                    if self.idx == self.frame_size:                  # 数组下标=帧长度  接收完毕
                        crc = get_sum(self.cache[0:-2])              # 计算校验和 [0:-2]从第0个数据到倒数第3个数据
                        logger.info('sum:0x%x[->0x%x]' % (self.cache[-2], crc))
                        if crc == self.cache[-2] and self.cache[-1] == 0x16: # 数据接收无误，开始获取数据组返回帧
                            # self.decode_cb(self.cache, ERR_NONE)  
                            print("数据接收无误,可以上传到PC端了")
                            temp_data = self.cache[14:-2]                    # <class 'bytearray'>  相应帧的具体数据（未减33H的数据）
                            list_data = list(temp_data)                      # <class 'list'>  bytearray不能循环减33H
                            self.di = self.decode_di()                       # 数据域减33，获取到数据标志
                            self.data = self.decode_645data_list(list_data)  # 数据域减33，获取到设备上传的数据
                            # print("-----------------------------------data---------------------")
                            # print(type(self.data))
                            print(f"接收到的di:{self.di},接收到的data:{self.data}")
                        else:
                            logger.error('sum:0x%x[->0x%x]' % (self.cache[-2], crc)) # 接收错误，打印错误信息
                        
                        self.addr = bytearray(0)                                     # 清空数据缓存，重新开始接收
                        self.cache = bytearray(0)
                        self.idx = 0
                        self.st = ST_IDLE

            return self.data
            
        except Exception as e:#打印错误
            print(e)
    
    
    def decode_cb(self, data, err):
        pass
# if __name__ == '__main__':
#     dlt645 = dlt_645_2007()
#     encode_data = dlt645.encode(0x11,addr,data_temp)
#     dlt645.decode(encode_data)
#----------------------------测试函数----------------------------------#
""" 
def get_u32_val(idx):
    src1 = datetime.datetime.now()
    val = src1.day * 1000000 + src1.hour * 10000 + src1.minute * 100 + ((src1.second%10)*10)
    src = '%08d' % (val + idx)
    msg = bytearray(4)
    msg[0] = (int(src[7]) | (int(src[6])) << 4) + 0x33
    msg[1] = (int(src[5]) | (int(src[4])) << 4) + 0x33
    msg[2] = (int(src[3]) | (int(src[2])) << 4) + 0x33
    msg[3] = (int(src[1]) | (int(src[0])) << 4) + 0x33
    return src, msg
def get_u16_val(idx):
    src1 = datetime.datetime.now()
    val = (src1.minute * 100 + ((src1.second%10)*10))
    src = '%04d' % (val + idx)
    msg = bytearray(2)
    msg[0] = (int(src[3]) | (int(src[2])) << 4) + 0x33
    msg[1] = (int(src[1]) | (int(src[0])) << 4) + 0x33
    return src, msg
def get_u16_val1(idx):
    src1 = datetime.datetime.now()
    val = ((src1.second%10)*10)
    src = '%04d' % (val + idx)
    msg = bytearray(2)
    msg[0] = (int(src[3]) | (int(src[2])) << 4) + 0x33
    msg[1] = (int(src[1]) | (int(src[0])) << 4) + 0x33
    return src, msg
def get_u24_val(idx):
    src1 = datetime.datetime.now()
    val = (src1.hour * 10000 + src1.minute * 100 + ((src1.second%10)*10))
    src = '%06d' % ((val + idx))
    msg = bytearray(3)
    msg[0] = (int(src[5]) | (int(src[4])) << 4) + 0x33
    msg[1] = (int(src[3]) | (int(src[2])) << 4) + 0x33
    msg[2] = (int(src[1]) | (int(src[0])) << 4) + 0x33
    return src, msg
def get_u24_val1(idx):
    src1 = datetime.datetime.now()
    val = (src1.minute * 100 + ((src1.second%10)*10))
    src = '%06d' % ((val + idx)*10)
    msg = bytearray(3)
    msg[0] = (int(src[5]) | (int(src[4])) << 4) + 0x33
    msg[1] = (int(src[3]) | (int(src[2])) << 4) + 0x33
    msg[2] = (int(src[1]) | (int(src[0])) << 4) + 0x33
    return src, msg
def get_time_bin():
    msg = bytearray(5)
    src1 = datetime.datetime.now()
    temp = '%02d' % src1.minute
    idx = 0
    msg[idx] = (int(temp[1]) | (int(temp[0])) << 4) + 0x33
    idx += 1
    temp = '%02d' % src1.hour
    msg[idx] = (int(temp[1]) | (int(temp[0])) << 4) + 0x33
    idx += 1
    temp = '%02d' % src1.day
    msg[idx] = (int(temp[1]) | (int(temp[0])) << 4) + 0x33
    idx += 1
    temp = '%02d' % src1.month
    msg[idx] = (int(temp[1]) | (int(temp[0])) << 4) + 0x33
    idx += 1
    temp = '%02d' % (src1.year - 2000)
    msg[idx] = (int(temp[1]) | (int(temp[0])) << 4) + 0x33
    return msg
 """
#----------------------------测试函数----------------------------------#
# class main_fn(dlt_645_2007): #这是解码程序，不是组码
#     def decode_cb(self, data, err):
#         if err != ERR_NONE:  #如果有错误，停止执行
#             return

#         ctrl = data[8]         #控制码为固定的第八个  从0开始数起
#         data_len = data[9]     #数据长度为固定的第九个
#         dot = bytearray(4)
#         dot[0] = decode_data_unit(data[10])
#         dot[1] = decode_data_unit(data[11])
#         dot[2] = decode_data_unit(data[12])
#         dot[3] = decode_data_unit(data[13])

#         id = u32_lit.unpack_from(dot, 0)[0]  #unpack_from(buffer, offset=0)  将打包的字节写入从offset偏移开始的可写缓冲buffer
#         val_name = ''

#         if ctrl == 0x11 and data_len == 4:
#             if id == 0x0201ff00:   # '电压数据块'
#                 msg = bytearray(0) #bytearry像C中的数组缓存区 ，只不过叫可变字节序列
#                 temp, val = get_u16_val(0)
#                 val_name = temp
#                 msg.extend(bytearray(data[10:14]))
#                 msg.extend(val)
#                 temp, val = get_u16_val(1)
#                 msg.extend(val)
#                 temp, val = get_u16_val(2)
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#                 pass
#             elif id == 0x0202ff00:  # 电流数据块
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u24_val1(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u24_val1(1)
#                 msg.extend(val)
#                 temp, val = get_u24_val1(2)
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#                 pass
#             elif id == 0x0205ff00:  # '瞬时视在功率'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u24_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u24_val(1)
#                 msg.extend(val)
#                 temp, val = get_u24_val(2)
#                 msg.extend(val)
#                 temp, val = get_u24_val(3)
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x0203ff00:  # '瞬时总有功功率数据块'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u24_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u24_val(1)
#                 msg.extend(val)
#                 temp, val = get_u24_val(2)
#                 msg.extend(val)
#                 temp, val = get_u24_val(3)
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x0204ff00:  # '瞬时总无功功率数据块'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u24_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u24_val(1)
#                 msg.extend(val)
#                 temp, val = get_u24_val(2)
#                 msg.extend(val)
#                 temp, val = get_u24_val(3)
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x0205ff00:  # '瞬时总视在功率'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u24_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u24_val(1)
#                 msg.extend(val)
#                 temp, val = get_u24_val(2)
#                 msg.extend(val)
#                 temp, val = get_u24_val(3)
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x0206ff00:  # '功率因数数据块'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u16_val1(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u16_val1(1)
#                 msg.extend(val)
#                 temp, val = get_u16_val1(2)
#                 msg.extend(val)
#                 temp, val = get_u16_val1(3)
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x02800001:  # '零线电流'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u24_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x0001ff00:  # '正向有功总电能数据块'
#                 msg = bytearray(0)
#                 temp, val = get_u32_val(0)
#                 val_name = temp
#                 msg.extend(bytearray(data[10:14]))
#                 msg.extend(val)
#                 temp, val = get_u32_val(1)
#                 msg.extend(val)
#                 temp, val = get_u32_val(2)
#                 msg.extend(val)
#                 temp, val = get_u32_val(3)
#                 msg.extend(val)
#                 temp, val = get_u32_val(4)
#                 msg.extend(val)
#                 msg.extend(get_time_bin())
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x0002ff00:  # '反向有功总电能'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u32_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u32_val(1)
#                 msg.extend(val)
#                 temp, val = get_u32_val(2)
#                 msg.extend(val)
#                 temp, val = get_u32_val(3)
#                 msg.extend(val)
#                 temp, val = get_u32_val(4)
#                 msg.extend(val)
#                 msg.extend(get_time_bin())
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x0003ff00:  # '组合无工1总电能'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u32_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u32_val(1)
#                 msg.extend(val)
#                 temp, val = get_u32_val(2)
#                 msg.extend(val)
#                 temp, val = get_u32_val(3)
#                 msg.extend(val)
#                 temp, val = get_u32_val(4)
#                 msg.extend(val)
#                 msg.extend(get_time_bin())
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x0004ff00:  # '组合无工2总电能'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u32_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u32_val(1)
#                 msg.extend(val)
#                 temp, val = get_u32_val(2)
#                 msg.extend(val)
#                 temp, val = get_u32_val(3)
#                 msg.extend(val)
#                 temp, val = get_u32_val(4)
#                 msg.extend(val)
#                 msg.extend(get_time_bin())
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x01010000:  # '正向有功总最大需量及发生时间'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u24_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 msg.extend(get_time_bin())
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x040005ff:  # '运行状态字'
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u16_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 temp, val = get_u16_val(1)
#                 msg.extend(val)
#                 temp, val = get_u16_val(2)
#                 msg.extend(val)
#                 temp, val = get_u16_val(3)
#                 msg.extend(val)
#                 temp, val = get_u16_val(4)
#                 msg.extend(val)
#                 temp, val = get_u16_val(5)
#                 msg.extend(val)
#                 temp, val = get_u16_val(6)
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x02800002:  # '电网频率',
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u16_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)
#             elif id == 0x02800004:  # '当前有功需量',
#                 msg = bytearray(0)
#                 msg.extend(bytearray(data[10:14]))
#                 temp, val = get_u24_val(0)
#                 val_name = temp
#                 msg.extend(val)
#                 send = self.encode(0x91, self.addr, msg)
#                 self.com.write(send)

#             log = ''
#             if id in id_name_dir.keys(): #这里的意思是取出字典的键，也就是数据标识，而不是数据标识后面的中文标注
#                 log = '%s_%s 地址:%s 控制字:%x len:%d 值:%10s id:%s ' % (
#                 self.used, datetime.datetime.now(), binascii.hexlify(self.addr), ctrl, data_len, val_name,
#                 id_name_dir[id])
#             else:
#                 log = '%s_%s地址:%s 控制字:%x len:%d 值:%10s id:0x%08x ' % (
#                 self.used, datetime.datetime.now(), binascii.hexlify(self.addr), ctrl, data_len, val_name, id)
#             self.write_log(log)
#             print(log)
#         else:
#             print('not support:{}'.format(binascii.hexlify(data)))
    
#     def write_log(self, data):
#         log_file = os.path.join(os.path.dirname(sys.argv[0]),
#                                 self.used + datetime.datetime.now().strftime('DTL645_2007%Y%m%d_%H.txt'))
#         try:
#             hd = open(log_file, 'a', encoding='utf-8')
#             hd.write(data + '\n')
#             hd.close()
#         except:
#             print('管理员权限运行')
#             sys.exit()

#     def __init__(self):
#         super().__init__()
#         port_list = list(serial.tools.list_ports.comports())
#         port_name = {}
#         self.val = 1
#         idx = 0

#         dis_wdbg('------------------------', '绿')
#         dis_wdbg('DLT645-2007 模拟表', '红')
#         dis_wdbg('version: 0.0.0', '红')
#         dis_wdbg('auth: wzd', '红')
#         dis_wdbg('time: 2020-10-21', '红')
#         dis_wdbg('RS485参数：9600 E-8-1', '红')
#         dis_wdbg('------------------------', '绿')

#         dis_wdbg('------------------------', '绿')
#         dis_wdbg('序号  名称', '红')

#         for i in port_list:
#             port_name[idx] = i[0]
#             print('%2d    %s' % (idx, i[0]))
#             idx += 1

#         if idx == 0:
#             print('please insert Uart.')
#             sys.exit()
#         dis_wdbg('------------------------', '绿')

#         while True:
#             select = input('序号:')
#             try:
#                 val = int(select)

#                 if val == 9:
#                     sys.exit()
#                 elif val <= idx:
#                     self.used = port_name[val]
#                     break
#             except Exception as e:
#                 print(e)
#                 pass

#         self.com = serial.Serial()

#         try:
#             self.com.baudrate = 9600
#             self.com.port = self.used
#             self.com.bytesize = 8
#             self.com.parity = 'N'
#             self.com.stopbits = 1
#             self.com.timeout = 0.1
#             self.com.open()

#         except Exception as e:
#             logger.error(e)
#             self.com.close()
#             sys.exit()

#         # out = dlt_645_2007.encode('',0x11,addr,data_temp)
#         # self.com.write(out)

#         while True:
#             msg = self.com.read(19)

#             if len(msg):
#                 # logger.info(binascii.hexlify(msg))
#                 self.decode(msg)
# class COM:
#     def __init__(self, port, baud):
#         self.port = port
#         self.baud = int(baud)

#         self.open_com = None
#         self.log = WriteLog.WriteLog('ITC_LOG.LOG')
#         self.get_data_flag = True
#         self.real_time_data = ' '

#     # return real time data form com
#     def get_real_time_data(self):
#         return self.real_time_data

#     def clear_real_time_data(self):
#         self.real_time_data = ' '

#     # set flag to receive data or not
#     def set_get_data_flag(self, get_data_flag):
#         self.get_data_flag = get_data_flag

#     def open(self):
#         try:
#             self.open_com = serial.Serial(self.port, self.baud)
#         except Exception as e:
#             self.log.error('Open com fail:{}/{}'.format(self.port, self.baud))
#             self.log.error('Exception:{}'.format(e))

#     def close(self):
#         if self.open_com is not None and self.open_com.isOpen:
#             self.open_com.close()

#     def send_data(self, data):
#         if self.open_com is None:
#             self.open()
#         success_bytes = self.open_com.write(data.encode('UTF-8'))
#         return success_bytes

#     def get_data(self, over_time=30):
#         all_data = ' '
#         if self.open_com is None:
#             self.open()
#         start_time = time.time()
#         while True:
#             end_time = time.time()
#             if end_time - start_time < over_time and self.get_data_flag:
#                 data = self.open_com.read(self.open_com.inWaiting())
#                 # data = self.open_com.read() # read 1 size
#                 data = str(data)
#                 if data != ' ':
#                     self.log.info('Get data is:{}'.format(data))
#                     all_data = all_data + data
#                     print()
#                     self.real_time_data = all_data
#             else:
#                 self.set_get_data_flag(True)
#                 break
#         return all_data


# if __name__ == '__main__':

#     out = main_fn()
#     out_s= ''

#     for i in range(0,len(out)):
#         out_s = out_s + ' ' + (hex(int(out[i]))).upper()[2:].zfill(2)
#     print(out_s)
    
    #组帧
    # out = dlt_645_2007.encode('',0x11,addr,data_temp)
    
    # com = serial.Serial('COM7',9600)

    # com.write(out)
    
    
    
    # for i in range(0,len(out)):
    #     out_s = out_s + ' ' + (hex(int(out[i]))).zfill(2)
    # print(out_s)
    
    # for x in out_s:
    #     out1.append = hex(out_s[i])
    #     i += 1
    # out_hex = bytearray.fromhex(out)
    # print(out1)
