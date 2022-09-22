#这个文件用来测试部分代码是否可实现
#------------------------------------------------------------------------#
# import struct
import re
# test_data = [0x20,0x00,0x11,0xFF]
# data1 = b"\x20\x00\x11\xFF"
# id = test_data[0]<<32 | test_data[1]<<16 | test_data[2]<<8 | test_data[3] 
# a = 12334
# u32_lit = struct.Struct('<I')

# if __name__ == "__main__":
#     hex = '%02X' % a
#     id = u32_lit.unpack_from(data1, 0)[0]
#     print(id)
#     # if hex == '302E':
#     #     print('%02X' % a)
#------------------------------------------------------------------------#
#------------------------------------------------------------------------#

# import serial
# import threading
# import time


# def receive():
#     while True:
#         #这里stm32发送信息以什么为结尾？回车吗
#         tran_data1 = ord(serial_port.read() )
#         if tran_data1 == 90:
#             tran_data2 = ord(serial_port.read())
#             if tran_data2 == 90:
#                 ring = ord(serial_port.read())
#                 task = ord(serial_port.read())
#                 power = ord(serial_port.read())
#                 print("data receive success!")

# def send(mark,result):
#     while True:
#         data = bytearray([0x5a, 0x5a, mark,result, 0xb3])
#         print(data)
#         serial_port.write(data)
#         time.sleep(1)

# if __name__ == '__main__':
#     serial_port = serial.Serial(
#         port="COM5",
#         baudrate=115200,
#         bytesize=serial.EIGHTBITS,
#         parity=serial.PARITY_NONE,
#         stopbits=serial.STOPBITS_ONE,
#     )
#     # Wait a second to let the port initialize
#     time.sleep(0.5)
#     mark = 0
#     result = 1
#     t1 = threading.Thread(target=receive)
#     t2 = threading.Thread(target=send,args=[mark,result])
#     t1.start()
#     t2.start()
#     t1.join()
#     t2.join()
#------------------------------------------------------------------------#
# DI = {
#       # (di标识名称): ()(值字节长度, 小数位数读写控制码)(单位）(中文名称)
#       # 表A.1##############################################################
#       0x00000000: (40001,  ("kWh"), ("组合有功总电能")),
#       0x00000100: (40002,  ("kWh"), ("组合有功费率1电能")),
#       0x00000200: (40003,  ("kWh"), ("组合有功费率2电能")),
#       0x00000300: (40004,  ("kWh"), ("组合有功费率3电能")),
#       0x00000400: (40005,  ("kWh"), ("组合有功费率4电能")),
#       0x0000FF00: (40006,  ("kWh"), ("组合有功电能数据块"))
#     }
# if __name__ == '__main__':
#     di_keys = DI.keys()
#     di_valus = DI.values()
#     di_list = list(di_keys) #将字典中的键值转化为
#     index = 0
#     for i in di_list:  
#         if 0x0000FF00 == i:
#             di_judge = di_list[index]
#             print(index)
#         index = index+1
#     index = 0
def hex_to_str(b):
    s = ''
    for i in b:
        s += '{0:0>2}'.format(str(hex(i))[2:])
    return(s)
def mySplit1(str):
    t=''
    for i in range(int(len(str)/2)):
        t += str[2*i:2*(i+1)] + ' '
        t = t.upper()
    return t
def get_sum(data):    #校验和方式好像有点不太一样
    sum_val: int = 0
    for val in data:
        sum_val += val
    return sum_val % 256

def test(write_data):
    data_coil = bin(int(write_data,16))
    print("原始")
    print(data_coil)
    data_coil = data_coil[2:]
    print("data_coil[2:]")
    print(data_coil)
    res = data_coil[::-1]
    print("倒序")
    print(res)

    res = res[len(res) // 2:] + res[0:len(res) // 2]
    print("最终")
    print(res)
    return res

    # data_coil = re.findall(r'\w{2}', write_data)
    # data = data_coil.split(',')
    # print(data)


    # temp = int(data_coil,16)
    # temp1 = bin(temp)
    # data_coil_reverse0 = data_coil[0][::-1]
    # data_coil_reverse1 = data_coil[1][::-1]
    # data_coil_reverse2= data_coil[2][::-1]
    # print(data_coil_reverse0)
    # print(data_coil_reverse1)
    # print(data_coil_reverse2)


def str_reverse(data):
    lens = len(data)
    temp_data = bin(int(data, 16))
    temp_data = temp_data[2:]
    if lens > 0 and lens <= 2:
        res = temp_data[::-1]
    if lens > 2 and lens <= 4:
        res = temp_data[::-1]
        res = res[len(res) // 2:] + res[0:len(res) // 2]
    if lens > 4 and lens <= 6:
        res = []
        res1 = temp_data[:8]
        res1 = res1[::-1]
        res2 = temp_data[8:16]
        res2 = res2[::-1]
        res3 = temp_data[16:24]
        res3 = res3[::-1]
        res = res1 + res2 + res3
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

if __name__ == '__main__':
    # write_data = "CD010203"
    # print(len(write_data))
    # res = test(write_data[0:8])
    # print(type(res))
    res1 = str_reverse("CD010206")
    print(res1)
    #str_reverse("010203")

    #test("CD01")