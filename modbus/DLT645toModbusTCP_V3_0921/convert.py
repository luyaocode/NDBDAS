#该文件用来进行mb_TCP和645协议之间的转换

from winreg import HKEY_LOCAL_MACHINE
from MBTCP import *
from mb_TCP_regaddr import *
from DLT645 import *
from dlt645di import *
import re

class MBTCP_TO_DLT645(MBTCP):
    def __init__(self):
        super().__init__()  # 继承父类(MBTCP)的init方法
    
    
    def convert(self):

        cishu_reg = 0
        cishu_coil = 0
        cishu_data = 0

        #di_num = int(self.num, 16)                     #将字符型数量转化为int型，因为类MBTCP_TO_DLT645继承了类MBTCP，所以可以直接用self.num调用类MBTCP中的属性
        di_num = 0
        di_index = 0                                    # 索引浮标
        di_data = []                                    # 需要返回的645格式组帧字符串数组
        di_reg_values = list(TCP_to_645DI.values())     # dict.values()以列表返回字典中的所有值，modbus TCP协议中寄存器地址 对应 645协议中原本的数据标志
        di_coil_values = list(TCP_to_645Status.values())# dict.values()以列表返回字典中的所有值，modbus TCP协议中寄存器地址 对应 645协议中自定义的数据标志
        write_data_all = []                             # 写单个数据的数据域的数组
        data_register = []                              # 写多个寄存器的值的数组

        addr645 = bytearray()                           # 需要返回的645格式组帧字节数组
        addr645_index = 0                               # 数组下标
        addr645_values = list(TCP_to_645ADDR.values())  # modbus TCP协议中从机设备地址 对应 645协议设备地址
        regaddr = str(int(self.regaddr,16))             # 从MBTCP类中得到寄存器地址
        coiladdr = self.regaddr.zfill(5)                # 从MBTCP类中得到线圈地址 四位地址
        #write_num = int(self.vallen, 16)                # modbus TCP功能码01-04 没有这个，所以为‘’（空），在int（）会报错，将他们放在对应功能码下边


        for i in TCP_to_645ADDR.keys():        # 轮询字典里的键-------找设备地址--------
            print("------------------开始地址转换----------------------")
            if self.slaveid == i:
                addr645_hexstr = addr645_values[addr645_index]  # 将 addre645数据加入到数组中
                addr645_temp = bytearray.fromhex(addr645_hexstr)    # 将字符串变成16进制的字符
                addr645.extend(addr645_temp)
                print(f"字典中第{addr645_index}个键，对应值为:{addr645_hexstr}")
                break
            addr645_index = addr645_index + 1   # 不是就接着往后轮询
        addr645_index = 0   #   都轮询一遍之后，索引变为0


        if self.funcode in (ReadCoil,ReadDispersion,ReadHoldReg,ReadInputReg):  # 即功能码为01-04时
            di_num = int(self.num, 16)
            print("------------------开始功能码转换----------------------")
            ctrl = ReadData     # dlt645di.py中有规定各控制码
            if di_num == 1:     # 在MBTCP.py中的decode_requ（）函数中，会解析出self.num
                print("只读一个数据")
                for a1 in TCP_to_645DI.keys():                      # 遍历字典
                    if regaddr == a1:                               # 如果寄存器地址在字典中
                        di_hexstr = di_reg_values[di_index][0]      # 取出寄存器地址对应的数据标志字符串
                        di_data.append(di_hexstr)                   # 将字符串添加到数组中以供主函数处理调用
                        # di_temp = bytearray.fromhex(di_hexstr)    # 将字符串转为bytearry 16进制数组型
                        # di_data.extend(di_temp)                   # 添加到返回的bytearry数组中
                        print(f"字典中第{di_index}个键，对应值为:{di_hexstr}")
                        break
                    di_index = di_index + 1
                di_index = 0
            else:         
                print("读多个数据")
                for a2 in TCP_to_645DI.keys():                         # 遍历645数据标识字典
                    if regaddr == a2:                                  # 如果寄存器地址在字典中
                        while cishu_reg < di_num:                         # 开始将对应值循环写入到di_data数组中
                            print("开始读入多个数据")
                            di_hexstr = di_reg_values[di_index+cishu_reg][0]  # di_index+cishu：0+0,0+1,0+2，下次循环就是1+0,1+1,1+2读多个线圈就要有多个寄存器，当读到40001时，一次读取三个，那么就是40001  40002  40003  di_hexstr应该是会更新的
                            di_data.append(di_hexstr)                 # 循环写入di数组
                            cishu_reg = cishu_reg + 1
                            print(f"第{cishu_reg}读入,字典中第{di_index}个键，对应值为:{di_hexstr}")
                        break
                    di_index = di_index + 1
                di_index = 0



        elif self.funcode in (WriteSignalCoil,WriteSignalHoldReg,WriteMultipleCoil,WriteMultipleHoldReg): #写入数据的转换
            pwd = ('01020304')
            UserName = ('01020304')
            #write_data = bin(int(self.val, 16))[2:].zfill(6 * 4)
            write_data = self.val
            #ctrl = WriteData

            def str_reverse(data):           # 写线圈 每两位的16进制字符数据变成二进制之后倒序（数组下标不动，只是8位二进制数据倒序）
                lens = len(data)
                temp_data = bin(int(data,16))
                temp_data = temp_data[2:]    # 去掉0b
                if lens > 0 and lens <= 2:   # 只有两个字节的16进制字符，自己本身倒序就可以了
                    res = temp_data[::-1]
                if lens > 2 and lens <= 4:   # 4个字节的16进制字符，按位（两个字节一起）倒序
                    res = temp_data[::-1]    # 所有数据倒序
                    res = res[len(res) // 2:] + res[0:len(res) // 2]    # 调换两个字节的顺序
                if lens > 4 and lens <= 6:   # 6个字节的16进制字符，按位（两个字节一起）倒序
                    res = []
                    res1 = temp_data[:8]     # 按位倒序
                    res1 = res1[::-1]
                    res2 = temp_data[8:16]
                    res2 = res2[::-1]
                    res3 = temp_data[16:24]
                    res3 = res3[::-1]
                    res = res1 + res2 + res3    # 按位倒序之后重组拼接在一起
                if lens > 6 and lens <= 8:
                    res = []
                    res1 = temp_data[:8]
                    res1 = res1[::-1]
                    res2 = temp_data[8:16]
                    res2 = res2[::-1]
                    res3 = temp_data[16:24]
                    res3 = res3[::-1]
                    res4 = temp_data[24:32]
                    res4 = res4[::-1]
                    res = res1 + res2 + res3 + res4
                return res


            if self.funcode == WriteSignalHoldReg:
                print("------------------开始写单个保持寄存器----------------------")
                di_num = 1  # 写单个保持寄存器或单个线圈的时候没有这一位，所以不能放在最前边声明，自己给他赋一个值，因为在main中会根据di_num判断发几次命令帧，所以写单个线圈以及写单个保持寄存器的时候要给的值为1
                ctrl = WriteData
                print("只写一个保持寄存器")
                for b1 in TCP_to_645DI.keys():                      # 遍历字典
                    if regaddr == b1:                               # 如果寄存器地址在字典中
                        di_hexstr = di_reg_values[di_index][0]      # 取出寄存器地址对应的数据标志字符串
                        di_data.append(di_hexstr)                   # 将字符串添加到数组中以供主函数处理调用

                        write_data_all.append(di_hexstr)            # 组发送的数据域
                        write_data_all.append(pwd)
                        write_data_all.append(UserName)
                        write_data_all.append(write_data)

                        print(f"字典中第{di_index}个键，对应值为:{di_hexstr}")
                        print(f"主站的操作人员代码为{UserName}")
                        print(f"主站要写的数据为{write_data}")
                        break
                    di_index = di_index + 1
                di_index = 0

            if self.funcode == WriteSignalCoil:
                print("------------------开始写单个线圈----------------------")
                di_num = 1
                ctrl = WriteData
                print("只写一个线圈")
                for b2 in TCP_to_645Status.keys():                       # 遍历字典
                    if coiladdr == b2:                                    # 如果线圈地址在字典中
                        di_hexstr = di_coil_values[di_index][0]          # 取出线圈地址对应的数据标志字符串
                        di_data.append(di_hexstr)                        # 将字符串添加到数组中以供主函数处理调用

                        write_data_all.append(di_hexstr)
                        write_data_all.append(pwd)
                        write_data_all.append(UserName)
                        write_data_all.append(write_data)

                        print(f"字典中第{di_index}个键，对应值为:{di_hexstr}")
                        print(f"主站的操作人员代码为{UserName}")
                        print(f"主站要写的数据为{write_data}")
                        break
                    di_index = di_index + 1
                di_index = 0

            if self.funcode == WriteMultipleCoil:
                ctrl = WriteData
                di_num = int(self.num, 16)
                write_data_num = int(self.vallen, 16)                 # 写线圈寄存器的个数
                write_data_reverse = str_reverse(write_data)          # 已经将数据按位倒序
                data_coil = re.findall(r'\w{1}', write_data_reverse)  # 将倒序的str变成list['','',....]，一个线圈寄存器中对应一个写的数据，所以要把得到的一串数一个一个分开
                print("打印每一位的写的数据")
                print(data_coil)                                      #list型数据
                print("------------------写多个线圈------------------")
                for b3 in TCP_to_645Status.keys():  # 遍历645设备状态字典
                    if coiladdr == b3:  # 如果线圈地址在字典中
                        while cishu_coil < di_num:  # 开始将对应值循环写入到di_data数组中
                            print("开始写入多个线圈状态")
                            di_hexstr = di_reg_values[di_index + cishu_coil][0]  # di_index+cishu：0+0,0+1,0+2，下次循环就是1+0,1+1,1+2读多个线圈就要有多个寄存器，当读到40001时，一次读取三个，那么就是40001  40002  40003  di_hexstr应该是会更新的
                            di_data.append(di_hexstr)            # di循环写入di数组

                            write_data_all.append(di_hexstr)     # 拼接数据域
                            write_data_all.append(pwd)
                            write_data_all.append(UserName)
                            write_data_all.append(data_coil[cishu_coil])  # 此时write_data_all的['00000000', '01020304', '01020304', '000A', '00000100', '01020304', '01020304', '0102']

                            cishu_coil = cishu_coil + 1
                            if cishu_coil == 1:
                                print(f"用户{UserName}，总共写入的数据为{write_data}")
                            print(f"第{cishu_coil}次写入,字典中第{di_index}个键，对应值为:{data_coil[cishu_coil]}")
                        break
                    di_index = di_index + 1
                di_index = 0

            if self.funcode == WriteMultipleHoldReg:
                ctrl = WriteData
                di_num = int(self.num, 16)
                data_register = re.findall(r'\w{4}', write_data)                 # 一个寄存器中存放两个字节中的数据 类['000A', '0102']
                print("------------------写多个保持寄存器------------------")   
                for b4 in TCP_to_645DI.keys():                                   # 遍历645数据标识字典
                    if regaddr == b4:                                            # 如果寄存器地址在字典中
                        print("开始写入多个数据")   
                        while cishu_reg < di_num:                                # 开始将对应值循环写入到di_data数组中
                            di_hexstr = di_reg_values[di_index + cishu_reg][0]   # di_index+cishu：0+0,0+1,0+2，下次循环就是1+0,1+1,1+2读多个线圈就要有多个寄存器，当读到40001时，一次读取三个，那么就是40001  40002  40003  di_hexstr应该是会更新的
                            di_data.append(di_hexstr)                            # di循环写入di数组
                            data_each_register = data_register[cishu_reg]        # 写的数据放到按顺序对应的寄存器中

                            write_data_all.append(di_hexstr)  # 拼接数据域
                            write_data_all.append(pwd)
                            write_data_all.append(UserName)
                            write_data_all.append(data_each_register)            #此时write_data_all的['00000000', '01020304', '01020304', '000A', '00000100', '01020304', '01020304', '0102']

                            cishu_reg = cishu_reg + 1
                            if cishu_reg == 1:
                                print(f"用户{UserName}，总共写入的数据为{write_data}")
                            print(f"第{cishu_reg}次写入,字典中第{di_index}个键，对应值为:{data_each_register}")
                        break
                    di_index = di_index + 1
                di_index = 0


        return addr645, ctrl, di_data, di_num,write_data_all,data_register # di 数据标识位，返回的是数组,因为有读多个的情况，需要在main.py中进行转换

        


