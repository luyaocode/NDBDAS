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
################接收缓存区变脸###################
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

    def Recive645Data(self):
        global recv_count, read_spdata
        while True:
            recv_count = self.engine.inWaiting()  # 接收缓存区字节数
            if recv_count > 0:
                print("--------------串口接收到645响应帧,开始上行传输响应帧----------------------")
                read_spdata = self.engine.readline()  # 读取16进制帧结构
                self.print_hex_list(read_spdata, endl=1)  # 将接收的响应帧打印出来
                decode_data = dlt645.decode(read_spdata)  # 传出list型数据数组，进行modbusTCP组帧
                mb_resp_list = mbtcp2dlt645.encode_resp(decode_data)  # 组modbusTCP格式响应帧
                print(f"转换的mb_resp帧为: {mb_resp_list}")
                mb_resp = " ".join(mb_resp_list)
                mb_resp_bytes = bytes(mb_resp.encode('utf-8'))
                modbus_tcp_engine.con.send(mb_resp_bytes)  # 上行发送命令到JAVA端

    def Send645Data(self):
        global addr645, ctrl, di_data, di_num, read_spdata, recv_count
        i = 0
        while True:
            di_bytearry = bytearray()
            if addr645 != "" and ctrl != 0 and di_data != None and di_num != 0:  # 如果数据转换成功
                print("--------------数据转换成功,开始组下行传输命令帧----------------------")
                if di_num == 1:
                    print("发送一次命令")
                    di_temp = bytearray.fromhex(di_data[0])
                    di_bytearry.extend(di_temp)
                    encode_data = dlt645.encode(addr645, ctrl,
                                                di_bytearry)  # 组645格式的命令帧 这里数据域未加33需要补上 为了便于查看，测试阶段目前未加33， 使用时一定要加上33！！！！！
                    self.engine.write(encode_data)
                    print(f"645命令帧为:{encode_data}")
                else:
                    print("发送多次命令")
                    while i < di_num:
                        di_temp = bytearray.fromhex(di_data[i])
                        di_bytearry.extend(di_temp)
                        encode_data = dlt645.encode(addr645, ctrl,
                                                    di_bytearry)  # 组645格式的命令帧 这里数据域未加33需要补上 为了便于查看，测试阶段目前未加33， 使用时一定要加上33！！！！！
                        di_bytearry = bytearray()  # 发送完数组清空
                        if i == 0:
                            print("发送第一条命令")
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
        global addr645, ctrl, di_data, di_num, di_bytearry
        while True:
            recv_data = self.con.recv(1024)  # 接收数据
            print("----------------接收到上位机命令----------------------")
            print(f"recv_data: {recv_data}")
            if recv_data == b"quit":  # 接收到quit就断开socket连接
                break
            recv_str = str(recv_data, encoding="utf-8")  # 字节串转字符串,为了方便modbusTCP解帧
            recv_ss = recv_str.split(" ")  # 转成字符串数组

            mbtcp2dlt645.requ_cache.clear()  # 缓冲区预处理
            mbtcp2dlt645.resp_cache.clear()  # 缓冲区预处理

            mbtcp2dlt645.decode_requ(recv_ss)  # 解析请求帧（提取相关属性）
            print(f"打印当前MBTCP的缓存区: {mbtcp2dlt645.requ_cache}")

            addr645, ctrl, di_data, di_num = mbtcp2dlt645.convert()  # 协议转换
            print(f"协议转换成功 addr645:{addr645},ctrl:{hex(ctrl)},di_data:{di_data}")


if __name__ == '__main__':
    # 实例初始化
    dlt645 = dlt_645_2007()
    mbtcp2dlt645 = MBTCP_TO_DLT645()

    # 开启引擎
    modbus_tcp_engine = Communication_MBTCP('10.1.10.63', 9090, 5)
    serial_engine = Communication_645("COM4", 115200, 0.5)

    # 开启线程
    tcp_recv_thread = threading.Thread(target=modbus_tcp_engine.tcplink)
    serial_send_thread = threading.Thread(target=serial_engine.Send645Data)
    serial_recv_thread = threading.Thread(target=serial_engine.Recive645Data)

    tcp_recv_thread.start()
    serial_send_thread.start()
    serial_recv_thread.start()
