errno_str = "err"


# 仅供测试，模拟响应帧最后需要响应的数据，线圈状态或者电参量
def getflag(n):
    if n == 1:
        return "ff"
    if n == 2:
        return "ffff"
    if n == 6:
        return "aabbccddeeff"
    if n == 10:
        return "0102030405060708090A"
    return errno_str

class MBTCP:
    # tcp请求帧格式
    #   MBAP:事务处理标识（rand,2字节）+协议标识（prot,2字节）+长度（taillen,2字节）+设备标识（slaveid,1字节）
    #   PDU :
    #       01~04：操作码（funcode,1字节）+线圈或寄存器地址（addr,2字节）+线圈或者寄存器数量（num，2字节）
    #       05~06：操作码（funcode,1字节）+线圈或寄存器地址（addr，2字节）+线圈或者寄存器值（val，2字节，2字节是因为寄存器可存放2字节数据）
    #       0f，10：操作码（funcode，1字节）+线圈或寄存器地址（addr，2字节）+线圈或者寄存器数量（num，2字节）+数据长度（vallen,1字节）+线圈或者寄存器值（val，不定）
    # 初始化
    def __init__(self):
        self.requ_cache = []  # 向下提供请求帧关键数据：funcode,slaveid,addr,num,val，要求int数组形式
        self.resp_cache = []  # 向上提供响应帧完整数据
        # MBAP
        self.rand = ''        
        self.port = "0000"
        self.taillen = ''
        self.slaveid = ''
        # PDU
        self.funcode = ''     # 必须
        self.regaddr = ''     # 必须
        self.num = ''
        self.val = ''
        self.vallen = ''

    # 解析请求--解析向下的请求帧---将645需要的属性放在一个list中，方便读取
    def decode_requ(self, ss):
        self.rand = ss[0]
        rand_int = int(self.rand, 16)
        if rand_int < 0 or rand_int > 65536:
            print("随机码错误！")
            return
        self.port = ss[1]
        if int(self.port, 16) != 0:
            print("不是tcp协议！")
            return
        self.taillen = ss[2]
        self.slaveid = ss[3]
        self.funcode = ss[4]
        funcode_int = int(self.funcode, 16)
        self.regaddr = ss[5]
        if funcode_int in (1, 2, 3, 4):     # 操作码1-4，帧的第7位为线圈或寄存器的数量
            self.num = ss[6]
        elif funcode_int in (5, 6):         # 操作码5-6，帧的第7位为线圈或寄存器的值
            self.val = ss[6]
        elif funcode_int in (15, 16):       # 操作码15-16，帧的第7位为线圈或寄存器的数量
            self.num = ss[6]
            self.vallen = ss[7]             # 帧的第8位为数据长度
            self.val = ss[8]                # 帧的第9位为线圈或寄存器的值
        else:
            print("操作码错误！")
            return
        self.requ_cache.append(self.funcode)        # 数据块中存放的全是645协议需要的属性存放在list中
        self.requ_cache.append(self.slaveid)
        self.requ_cache.append(self.regaddr)
        self.requ_cache.append(self.num)
        self.requ_cache.append(self.val)
        # return self.requ_cache
    
    # 组发给modbus TCP的帧格式
    def encode_resp(self,data):     # data为645解析出来的响应数据
        # 写一个645到tcp的转换函数，存到self.resp_cache
        self.resp_cache.clear()     # list包含clean（）函数，将当前列表中的数据清空。
        if data != '':

            self.resp_cache.append(self.rand)
            self.resp_cache.append(self.port)
            self.resp_cache.append(self.slaveid)
            self.resp_cache.append(self.funcode)
            self.resp_cache.append(self.regaddr)
            #self.resp_cache.append("000000000")
            print(type(self.funcode))
            print(self.funcode)
            if self.funcode != '0F':    # 如果功能码不为写多线圈，那么响应的时候数据放在一起就可以了
                str_data = [str(hex(i))[2:].zfill(2) for i in data]
                print("打印转换后的data")
                print(str_data)
                test_str = "".join(str_data)  # 拼接字符串，依照modbus TCP协议的帧格式组帧发送
            else:       # 如果功能码为写多线圈，那么响应的时候数据要把寄存器数量与字节数分开返回
                str_data = [str(hex(i))[2:].zfill(2) for i in data]
                print('str_data')
                print(str_data)
                test_str = str_data[0] + str_data[1] + " " + str_data[2]
                # lens = len(test_str)
                # if lens == 4:
                #     test_str = " ".join(str_data)
            self.resp_cache.append(test_str)
        else:
            self.resp_cache.append(self.rand)
            self.resp_cache.append(self.port)
            self.resp_cache.append(self.slaveid)
            self.resp_cache.append(self.funcode)
            self.resp_cache.append(self.regaddr)
            #self.resp_cache.append("000000000")
        return self.resp_cache

    def response(self, ss):
        # 缓冲区预处理
        self.requ_cache.clear()
        self.resp_cache.clear()
        # 解析请求帧（给相关属性赋值）
        self.decode_requ(ss)
        # 组响应帧，并返回给主调函数
        return self.encode_resp()
