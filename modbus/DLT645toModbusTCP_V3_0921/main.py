from asyncore import write
from distutils.fancy_getopt import wrap_text
import serial
import socket
import sys
import time
import threading
from DLT645 import *
from dlt645di import *
from MBTCP import *
from convert import *

#####################工作模式####################
work_mode = 1
debug_mode = 2
fault_mode = 3
#################协议转换全局变量################
addr645 = bytearray()
ctrl = 0
di_data = []
di_num = 0
################接收缓存区变量###################
recv_count = 0
read_spdata = bytes()


#################################################

class Communication_645:
    def __init__(self, com, bps, timeout):
        self.port = com
        self.bps = bps
        self.timeout = timeout

        try:
            self.engine = serial.Serial(self.port, self.bps, timeout=self.timeout)
            if self.engine.is_open:
                print("Serial open success")
        except Exception as e:
            print("---Serial_error--- ", e)

    def print_hex_list(self, frame, endl=1):
        for x in frame:
            sys.stdout.write('%02x ' % (x))
        if endl:
            sys.stdout.write('\n')

    def Recive645Data(self):  # 将645数据转换成modbus TCP数据发送到平台
        global recv_count, read_spdata
        while True:
            recv_count = self.engine.inWaiting()  # 接收缓存区字节数
            if recv_count > 0:
                print("--------------串口接收到645响应帧,开始上行传输响应帧----------------------")
                read_spdata = self.engine.readline()  # 读取16进制帧结构
                self.print_hex_list(read_spdata, endl=1)  # 将接收的响应帧打印出来
                decode_data = dlt645.decode(read_spdata)  # 解析出645响应帧的数据，list类型的data
                mb_resp_list = mbtcp2dlt645.encode_resp(decode_data)  # 拿到响应的数据之后，组modbusTCP格式响应帧
                print(f"转换的mb_resp帧为: {mb_resp_list}")
                mb_resp = " ".join(mb_resp_list)
                mb_resp_bytes = bytes(mb_resp.encode('utf-8'))
                modbus_tcp_engine.con.send(
                    mb_resp_bytes)  # 上行发送命令到JAVA端     # modbus_tcp_engine.con在Communication_MBTCP类中定义

    def Send645Data(self):  # 将解析完的modbus TCP中的地址以及数据标识，组成645格式的帧发送到串口
        global addr645, ctrl, di_data, di_num, read_spdata, recv_count, write_data_all, data_register
        i = 0
        while True:
            di_bytearry = bytearray()  # Communication_MBTCP类中的tcplink函数返回需要的参数
            if addr645 != "" and ctrl == ReadData and di_data != None and di_num != 0:  # 如果数据转换成功
                print("--------------数据转换成功,开始组下行传输读数据命令帧----------------------")
                if di_num == 1:  # di_num是全局变量，在执行到Communication_MBTCP类中的tcplink函数的addr645,ctrl,di_data,di_num= mbtcp2dlt645.convert()时，被赋值
                    print("主站请求读一个数据")
                    di_temp = bytearray.fromhex(di_data[0])  # di_data是数据标识（一个或多个）
                    di_bytearry.extend(di_temp)
                    encode_data = dlt645.encode(addr645, ctrl, di_bytearry)  # 组645格式的命令帧，发给下位机
                    self.engine.write(encode_data)
                    print(f"645命令帧为:{encode_data}")
                else:
                    print("主站请求读多个数据")
                    while i < di_num:
                        di_temp = bytearray.fromhex(di_data[i])
                        di_bytearry.extend(di_temp)
                        encode_data = dlt645.encode(addr645, ctrl, di_bytearry)  # 组645格式的命令帧
                        di_bytearry = bytearray()  # 发送完数组清空
                        if i == 0:
                            print("发送第一条请求读多条数据命令")
                            self.engine.write(encode_data)
                            i = i + 1
                            print(f"645命令帧为:{encode_data}")
                        else:
                            if recv_count == 0 and read_spdata != b'':  # 初始时count = 0能够顺利进入 ,第二次发送时由于串口调用了inWaiting函数，count自动会清零了
                                read_spdata = bytes()  # 清空接收缓存字节数组
                                self.engine.write(encode_data)
                                print(f"645命令帧为:{encode_data}")
                                i = i + 1
                                # 清空缓存重新接收
                addr645 = bytearray()
                ctrl = 0
                di_data = []
                di_num = 0

            if addr645 != "" and ctrl == WriteData and di_data != None:  # 如果数据转换成功
                print("--------------数据转换成功,开始组下行传输写数据命令帧----------------------")
                if di_num == 1:
                    print("主站请求写一个数据")
                    di_temp0 = bytearray.fromhex(write_data_all[0])  # 组 写的数据域
                    di_temp1 = bytearray.fromhex(write_data_all[1])
                    di_temp2 = bytearray.fromhex(write_data_all[2])
                    di_temp3 = bytearray.fromhex(write_data_all[3])
                    di_bytearry.extend(di_temp0)
                    di_bytearry.extend(di_temp1)
                    di_bytearry.extend(di_temp2)
                    di_bytearry.extend(di_temp3)
                    encode_data = dlt645.encode(addr645, ctrl, di_bytearry)  # 组645格式的命令帧，发给下位机
                    self.engine.write(encode_data)
                    print(f"645命令帧为:{encode_data}")
                else:
                    print("主站请求写多个数据")
                    b = []
                    for j in range(0, len(write_data_all), 4):  # 写多个数据时，write_data_all中存储的是数据标识1，密码，操作者代码，写的具体数据1，。。。
                        b.append(write_data_all[j:j + 4])  # 4个一组，分别组帧发送到下位机
                    print("对645帧的数据域进行分组重装：")
                    print(b)
                    while i < di_num:
                        di_temp0 = bytearray.fromhex(b[i][0])  # 组 写的数据域
                        di_temp1 = bytearray.fromhex(b[i][1])
                        di_temp2 = bytearray.fromhex(b[i][2])
                        di_temp3 = bytearray.fromhex(
                            b[i][3].zfill(2))  # 写线圈的时候，每位写一位数据，不符合16进制字符，进而组帧不成功，所有将不足2位的数据进行高位补0；但是写寄存器就不用，已经满足了2位数据
                        di_bytearry.extend(di_temp0)
                        di_bytearry.extend(di_temp1)
                        di_bytearry.extend(di_temp2)
                        di_bytearry.extend(di_temp3)

                        encode_data = dlt645.encode(addr645, ctrl, di_bytearry)  # 组645格式的命令帧
                        di_bytearry = bytearray()  # 发送完数组清空

                        if i == 0:
                            print("发送第一条写数据的命令")
                            print(encode_data)
                            self.engine.write(encode_data)
                            i = i + 1
                            print(f"645命令帧为:{encode_data}")
                        else:
                            if recv_count == 0 and read_spdata != b'':  # 初始时count = 0能够顺利进入 ,第二次发送时由于串口调用了inWaiting函数，count自动会清零了
                                print(f"发送第{i + 1}条写数据的命令")
                                print(encode_data)
                                read_spdata = bytes()  # 清空接收缓存字节数组
                                self.engine.write(encode_data)
                                print(f"其645命令帧为:{encode_data}")
                                i = i + 1
                # 清空缓存重新接收
                addr645 = bytearray()
                ctrl = 0
                di_data = []
                # di_num = 0


class Communication_MBTCP:
    def __init__(self, ip, port, time_out):
        self.ip = ip
        self.port = port
        self.tcp_server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.tcp_server.bind((self.ip, self.port))

        try:
            self.tcp_server.listen(time_out)
            print("Waiting for connection...")
            self.con, address = self.tcp_server.accept()
            if self.con is None:
                print("Server> 未连接到客户端")
            else:
                print(f"Server> 已与{address}建立连接")
        except Exception as e:
            print("---TCP_error--- ", e)

    def tcplink(self):
        global addr645, ctrl, di_data, di_num, di_bytearry, write_data_all, data_register  # 这个函数中的全局变量最后都被赋值，在Communication_645类中用到时，已经有对应的值了(在程序运行时，最先执行的函数是这个，详细可见main中的代码）
        while True:
            recv_data = self.con.recv(1024)  # 接收数据，每次最多接收1024个字节
            print("----------------接收到上位机命令----------------------")
            print(f"recv_data: {recv_data}")
            if recv_data == b"quit":  # 接收到quit就断开socket连接
                break
            recv_str = str(recv_data, encoding="utf-8")  # 字节串转字符串,为了方便modbusTCP解帧
            recv_ss = recv_str.split(" ")  # 通过空格将字符串进行分割，转成字符串数组

            mbtcp2dlt645.requ_cache.clear()  # 645->mbTCP的缓冲区预处理
            mbtcp2dlt645.resp_cache.clear()  # mbTCP->645的缓冲区预处理

            mbtcp2dlt645.decode_requ(recv_ss)  # 解析向下的请求帧---将645需要的属性放在一个list，即requ_cache中
            print(f"打印当前MBTCP的缓存区: {mbtcp2dlt645.requ_cache}")

            addr645, ctrl, di_data, di_num, write_data_all, data_register = mbtcp2dlt645.convert()  # 协议转换，将这些全局变量给赋值
            print(f"协议转换成功 addr645:{addr645},ctrl:{hex(ctrl)},di_data:{di_data},write_data_all:{write_data_all}")

            # 模拟循环发送响应帧
            # print(mbtcp2dlt645.rand)
            resp_data=mbtcp2dlt645.rand+' 0000'+''
            resp_data = b'0000 0000 0000 220.4 0000'
            self.con.send(recv_data)


if __name__ == '__main__':
    # 实例初始化
    dlt645 = dlt_645_2007()  # DLT645.py中的类的实例化
    mbtcp2dlt645 = MBTCP_TO_DLT645()  # convert.py中的类的实例化

    # 开启引擎-
    # modbus_tcp_engine = Communication_MBTCP('127.0.0.1',9090,5)
    modbus_tcp_engine = Communication_MBTCP('localhost', 9090, 5)

    # serial_engine = Communication_645("COM7", 115200, 0.5)

    # 开启线程
    tcp_recv_thread = threading.Thread(target=modbus_tcp_engine.tcplink)
    # serial_send_thread = threading.Thread(target = serial_engine.Send645Data)   #运行线程内容的前提条件是接收到下行传输且转化好的指令
    # serial_recv_thread = threading.Thread(target = serial_engine.Recive645Data)

    tcp_recv_thread.start()  # 程序运行结束，线程结束，不用join()也可以
    # serial_send_thread.start()
    # serial_recv_thread.start()
