package com.asurplus.mytcp;

import java.util.Random;

//帧操作工具

/**
 * 功能码	中文含义解析	    英文含义解析	            位/字操作	操作数量
 * 0x01	    读线圈状态	    READ COIL STATUS	    位操作	    单个或多个
 * 0x02	    读离散量输入状态	READ INPUT STATUS	    位操作	    单个或多个
 * 0x03	    读保持寄存器	    READ HOLDING REGISTER	字操作	    单个或多个
 * 0x04	    读输入寄存器	    READ INPUT REGISTER	    字操作	    单个或多个
 * 0x05	    写单个线圈	    WRITE SINGLE COIL	    位操作	    单个
 * 0x06	    写单个保持寄存器	WRITE SINGLE REGISTER	字操作	    单个
 * 0x0F	    写多个线圈	    WRITE MULTIPLE COIL	    位操作	    多个
 * 0x10	    写多个保持寄存器	WRITE MULTIPLE REGISTER	字操作	    多个
 */
public class FrameUtil {

    public static void main(String[] args) {
        String sendStr;
//        读
        sendStr = FramingRead(1, 1, 0, 8);
        System.out.println("[0x01读线圈]：" + sendStr);
//        sendStr=FramingRead(2,1,16,16);//没有0x02读离散量
        System.out.println(sendStr);

//        写单个线圈
        sendStr = FramingWriteOneCoil(1, 0, true);
        System.out.println(sendStr);
//        写单个保持寄存器
        sendStr = FramingWriteOneHoldingRegister(1, 1000, 7834);
        System.out.println(sendStr);
//        写多个线圈
        sendStr = FramingWriteCoils(1, 30001, 4, 0x0f);
        System.out.println(sendStr);

//        写多个保持寄存器
        sendStr = FramingWriteHoldingRegisters(1, 40001, 3, "aabbccddeeff");
        System.out.println(sendStr);
    }


    //    一、读取:01,02,03读电参量,04

    /**
     * @param funCode 操作码
     * @param slaveId 设备编号
     * @param offSet  起始地址
     * @param num     线圈/离散量/寄存器的个数
     * @return 请求帧
     */
    static String FramingRead(int funCode, int slaveId, int offSet, int num) {
        StringBuilder sb = new StringBuilder();
//        MBAP
        int seed = new Random().nextInt(65536);
//        System.out.println(seed);
        String[] strs = int2hex(seed);
//        for (String s : strs) {
//            System.out.println(s);
//        }
        sb.append(strs[0]).append(strs[1]).append(" ");//事务处理标识，2字节
        sb.append("0000").append(" ");//0000，2字节，表示Modbus TCP协议
        sb.append("0006").append(" ");//长度，2字节，表示后面还有6字节
        sb.append(int2hex2(slaveId)).append(" ");//设备标识符，1字节，设备号
//      PDU
        sb.append(int2hex2(funCode)).append(" ");//操作码，1字节
        sb.append(int2hex(offSet)[0]).append(int2hex(offSet)[1]).append(" ");//起始地址,2字节
        sb.append(int2hex(num)[0]).append(int2hex(num)[1]);//线圈数量,2字节

        return sb.toString();
    }

    //    二、写单个线圈：true:开，false：关
//    05，控制电气开关
    static String FramingWriteOneCoil(int slaveId, int offSet, boolean flag) {
        StringBuilder sb = new StringBuilder();
        String flagStr = "error";
//        MBAP
        int seed = new Random().nextInt(65536);
        String[] strs = int2hex(seed);
        sb.append(strs[0]).append(strs[1]).append(" ");//事务处理标识，2字节
        sb.append("0000").append(" ");//0000，2字节，表示Modbus TCP协议
        sb.append("0006").append(" ");//长度，2字节，表字示后面还有6节
        sb.append(int2hex2(slaveId)).append(" ");//设备标识符，1字节，设备号

        sb.append(int2hex2(5)).append(" ");//操作码，1字节
        sb.append(int2hex(offSet)[0]).append(int2hex(offSet)[1]).append(" ");//起始地址,2字节
        if (flag) {
            flagStr = "ff00";
        } else {
            flagStr = "0000";
        }
        sb.append(flagStr);//线圈状态,2字节
        return sb.toString();
    }

    //    三、写单个保持寄存器
//    06，写单个设备参数
    static String FramingWriteOneHoldingRegister(int slaveId, int offSet, int value) {
        StringBuilder sb = new StringBuilder();
//        MBAP
        int seed = new Random().nextInt(65536);
        String[] strs = int2hex(seed);
        sb.append(strs[0]).append(strs[1]).append(" ");//事务处理标识，2字节
        sb.append("0000").append(" ");//0000，2字节，表示Modbus TCP协议
        sb.append("0006").append(" ");//长度，2字节，表示后面还有6字节
        sb.append(int2hex2(slaveId)).append(" ");//设备标识符，1字节，设备号

        sb.append(int2hex2(6)).append(" ");//操作码，1字节
        sb.append(int2hex(offSet)[0]).append(int2hex(offSet)[1]).append(" ");//起始地址,2字节
        sb.append(int2hex(value)[0]).append(int2hex(value)[1]);//设置值,2字节
        return sb.toString();

    }

    //    四、写多个线圈
//    15，控制多个开关状态
    static String FramingWriteCoils(int slaveId, int offSet, int coilNum, int value) {
        StringBuilder sb = new StringBuilder();
        int coilOutputByteLen = 1 + (coilNum - 1) / 8;
        int tailLen = coilOutputByteLen + 7;
//         MBAP
        int seed = new Random().nextInt(65536);
        String[] strs = int2hex(seed);
        sb.append(strs[0]).append(strs[1]).append(" ");//事务处理标识，2字节
        sb.append("0000").append(" ");//0000，2字节，表示Modbus TCP协议
        sb.append(int2hex(tailLen)[0]).append(int2hex(tailLen)[1]).append(" ");//长度，2字节，表示后面还有多少字节
        sb.append(int2hex2(slaveId)).append(" ");//设备标识符，1字节，设备号
//        PDU
        sb.append(int2hex2(15)).append(" ");//操作码，1字节
        sb.append(int2hex(offSet)[0]).append(int2hex(offSet)[1]).append(" ");//起始地址,2字节
        sb.append(int2hex(coilNum)[0]).append(int2hex(coilNum)[1]).append(" ");//线圈数，2字节
        sb.append(int2hex2(coilOutputByteLen)).append(" ");//线圈输出字节长度，1字节，表示后面线圈输出值的字节长度
//        因为一次性控制开关的个数是个位数，所以1字节即可
        sb.append(int2hex2(value));

        return sb.toString();

    }

    //      五、写多个保持寄存器
//    16，写多个设备参数，设备暂时按照每个寄存器单元占2字节
    static String FramingWriteHoldingRegisters(int slaveId, int offSet, int registerNum, String value) {
        StringBuilder sb = new StringBuilder();
        int registerDataLen = registerNum * 2;
        int tailLen = registerDataLen + 7;
        //         MBAP
        int seed = new Random().nextInt(65536);
        String[] strs = int2hex(seed);
        sb.append(strs[0]).append(strs[1]).append(" ");//事务处理标识，2字节
        sb.append("0000").append(" ");//0000，2字节，表示Modbus TCP协议
        sb.append(int2hex(tailLen)[0]).append(int2hex(tailLen)[1]).append(" ");//长度，2字节，表示后面还有多少字节
        sb.append(int2hex2(slaveId)).append(" ");//设备标识符，1字节，设备号
//        PDU
        sb.append(int2hex2(16)).append(" ");//操作码，1字节
        sb.append(int2hex(offSet)[0]).append(int2hex(offSet)[1]).append(" ");//起始地址,2字节
        sb.append(int2hex(registerNum)[0]).append(int2hex(registerNum)[1]).append(" ");//寄存器数，2字节
        sb.append(int2hex2(registerDataLen)).append(" ");//寄存器数据字节长度，1字节，表示后面要写入多少字节
        sb.append(value);

        return sb.toString();

    }

    //    十进制转16进制，2字节
    static String[] int2hex(int n) {
        int[] ints = new int[2];
        String[] hex = new String[2];
        if (n < 256) {
            ints[1] = n;
            ints[0] = 0;
        } else {
            ints[1] = n & 0xff;
            ints[0] = n >> 8;
        }
        if (ints[1] < 16) {
            hex[1] = "0" + Integer.toHexString(ints[1]);
        } else {
            hex[1] = Integer.toHexString(ints[1]);
        }
        if (ints[0] < 16) {
            hex[0] = "0" + Integer.toHexString(ints[0]);
        } else {
            hex[0] = Integer.toHexString(ints[0]);
        }
        return hex;
    }

    //    十进制转16进制，1字节
    static String int2hex2(int n) {
        String str = "";
        if (n < 16) {
            str = "0" + Integer.toHexString(n);
        } else {
            str = Integer.toHexString(n);
        }
        return str;
    }
//  十进制转16进制，指定字节长度
//    static String[] int2hex3(int n,int length){
//        int[] ints=new int[length];
//        String[] hex=new String[length];
//        if(n<16){
//            ints[length-1]=n;
//        }
//        if()
//        return ss;
//    }
}
