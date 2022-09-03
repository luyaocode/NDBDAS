#该文件用来进行mb_TCP和645协议之间的转换

from winreg import HKEY_LOCAL_MACHINE
from MBTCP import *
from mb_TCP_regaddr import *
from DLT645 import *
from dlt645di import *

class MBTCP_TO_DLT645(MBTCP):
    def __init__(self):
        super().__init__() #继承父类的init方法
    
    
    def convert(self):

        cishu = 0

        di_num = int(self.num, 16)                      #将字符型数量转化为int型
        di_index  = 0                                   #索引浮标
        di_data   = []                                  #需要返回的645格式组帧字符串数组
        di_values = list(TCP_to_645DI.values())         #modbus TCP协议中寄存器地址 对应 645协议中的数据标志
    
        addr645 = bytearray()                           #需要返回的645格式组帧字节数组
        addr645_index = 0                               #索引浮标
        addr645_values = list(TCP_to_645ADDR.values())  #modbus TCP协议中从机设备地址 对应 645协议设备地址
        regaddr = str(int(self.regaddr,16))             #从MBTCP类中得到寄存器地址

        for i2 in TCP_to_645ADDR.keys():
            print("------------------开始地址转换----------------------")
            if self.slaveid == i2: 
                addr645_hexstr = addr645_values[addr645_index] #将addre645数据加入到数组中
                addr645_temp = bytearray.fromhex(addr645_hexstr)
                addr645.extend(addr645_temp)
                print(f"字典中第{addr645_index}个键，对应值为:{addr645_hexstr}")
                break
            addr645_index = addr645_index + 1
        addr645_index = 0

        if self.funcode in (ReadCoil,ReadDispersion,ReadHoldReg,ReadInputReg):
            print("------------------开始功能码转换----------------------")
            ctrl = ReadData 
            if di_num == 1: 
                print("只读一个数据")
                for i1 in TCP_to_645DI.keys():                      #遍历字典
                    if regaddr == i1:                               #如果寄存器地址在字典中
                        di_hexstr = di_values[di_index][0]          #取出寄存器地址对应的数据标志字符串
                        di_data.append(di_hexstr)                   #将字符串添加到数组中以供主函数处理调用
                        # di_temp = bytearray.fromhex(di_hexstr)    #将字符串转为bytearry 16进制数组型
                        # di_data.extend(di_temp)                   #添加到返回的bytearry数组中
                        print(f"字典中第{di_index}个键，对应值为:{di_hexstr}")
                        break
                    di_index = di_index + 1
                di_index = 0
            else:         
                print("读多个数据")
                for i in TCP_to_645DI.keys():                         #遍历字典
                    if regaddr == i:                                  #如果寄存器地址在字典中
                        while cishu < di_num:                         #开始将对应值循环写入到di_data数组中
                            print("开始写入多个数据")
                            di_hexstr = di_values[di_index+cishu][0]  #di_hexstr应该是会更新的
                            di_data.append(di_hexstr)                 #循环写入di数组
                            cishu = cishu + 1
                            print(f"第{cishu}写入,字典中第{di_index}个键，对应值为:{di_hexstr}")
                        break
                    di_index = di_index + 1
                di_index = 0
        elif self.funcode in (WriteSignalCoil,WriteSignalHoldReg,WriteMultipleCoil,WriteMultipleHoldReg): #写入数据的转换先不做处理
            ctrl = WriteData
        
        return addr645,ctrl,di_data,di_num    #di 数据标识位，返回的是数组,因为有读多个的情况，需要在main.py中进行转换

