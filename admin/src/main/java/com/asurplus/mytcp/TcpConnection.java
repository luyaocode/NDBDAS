package com.asurplus.mytcp;

import com.asurplus.myutil.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Future;

@Component
//@Scope("prototype")可以将springboot默认的单例模式改为多例模式
@Scope("prototype")
public class TcpConnection {
    private  Socket socket = null;
    private static final Logger log = LoggerFactory.getLogger(TcpConnection.class);

    //@SneakyThrows是lombok携带的异常处理机制
    @Async
    public Future<Socket> connect(String ip, Integer port) {
        String url = "[" + ip + ":" + String.valueOf(port) + "]";
        try {
            log.info(url+"开始连接...");
            socket = new Socket(ip, port);
            log.info(socket + "连接成功！");
            return new AsyncResult<>(socket);

        } catch (IOException | NullPointerException e) {
//            e.printStackTrace();
            log.info("连接失败！");
            return new AsyncResult<>(socket);
        } finally {

        }
    }

    /**
     * 接收数据
     */
    @Async
    public void receive(Socket socket) {
        try {
//            System.out.println("[receive]"+socket);
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            StringBuilder recvStr = new StringBuilder();
            int len;
//            String recvStr; //收到的字符串
//            String[] ss;    //字节码转字符串的数组
            while (true) {
                len = is.read(bytes);
                if (len == -1)
                    continue;
//                System.out.println(len);
//                ASCII转字符
                for (int i = 0; i < len; i++) {
                    char ch = (char) bytes[i];
                    recvStr.append(ch);
//                    System.out.print(ch);
//                    System.out.print(bytes[i]+" ");
                }
                System.out.println(socket + "响应帧：" + recvStr.toString());
//                提取响应参数，组成合适的sql语句
                String sql=null;
                sql="insert into test_table(resp_data) values('" + recvStr + "')";
                //                这里调用一个将字符串携带的信息写进数据库的工具类
                SqlUtil.insertUtil(sql);
                recvStr.delete(0, recvStr.length());
//                recvStr=new String(bytes,0,len);
//                System.out.println(recvStr);
//                ss = FrameUtil.FrameSplit(bytes, len);
//                System.out.println("\n服务端："+recvStr);
//                recvStr解析
//                for (String str : ss) {
//                    System.out.println(str);
//                }
            }
        } catch (IOException e) {
//            e.printStackTrace();
            log.info("接收响应失败！");
        }
    }

    /**
     * 发送数据
     */
    public static void send(Socket socket, String sendStr) {
        OutputStream os = null;
        PrintWriter pw = null;
        // 要发送给服务器的信息
        try {
//            System.out.println("[send]"+socket);
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            //           一、测试发送帧
//            0102-读
            if (sendStr == null) {
                sendStr = FrameUtil.FramingRead(1, 1, 0, 74);
            }
            System.out.println(socket + "请求帧：" + sendStr);
            pw.write(sendStr);
            pw.flush();
            //            int count = 1;
            //            while(count-->0) {
            ////                0102-读
            //                sendStr = FrameUtil.FramingRead(1, 1, 0, 74);
            //                System.out.println("请求帧：" + sendStr);
            //                pw.write(sendStr);
            //                pw.flush();
            //                sleep(2000);
            ////                0304-读
            //                sendStr=FrameUtil.FramingRead(3,1,40001,3);
            //                System.out.println("请求帧：" + sendStr);
            //                pw.write(sendStr);
            //                pw.flush();
            //                sleep(2000);
            ////                05-写单个线圈
            //                sendStr=FrameUtil.FramingWriteOneCoil(2,20232,true);
            //                System.out.println("请求帧：" + sendStr);
            //                pw.write(sendStr);
            //                pw.flush();
            //                sleep(2000);
            ////                06-写单个保持寄存器
            //                sendStr=FrameUtil.FramingWriteOneHoldingRegister(1,38421,996);
            //                System.out.println("请求帧：" + sendStr);
            //                pw.write(sendStr);
            //                pw.flush();
            //                sleep(2000);
            ////                15-写多个线圈
            //                sendStr=FrameUtil.FramingWriteCoils(1,10001,7,0xa);
            //                System.out.println("请求帧：" + sendStr);
            //                pw.write(sendStr);
            //                pw.flush();
            //                sleep(2000);
            ////                16-写多个寄存器
            //                sendStr=FrameUtil.FramingWriteHoldingRegisters(1,30671,2,"aabbccdd");
            //                System.out.println("请求帧：" + sendStr);
            //                pw.write(sendStr);
            //                pw.flush();
            //                sleep(2000);
            //            }

            //            socket.close();
            ////            手动发送请求，测试用
            //            String hand;
            //            Scanner sc=new Scanner(System.in);
            //            System.out.print("手动输入要发送的数据");
            //            while(sc.hasNext()){
            //                if((hand=sc.nextLine()).equals("\\quit")){
            //                    break;
            //                }else{
            //                    pw.write(hand);
            //                    pw.flush();
            //                    System.out.println("发送成功！");
            //                }
            //            }


            ////        写单个线圈
            //            sendStr = FrameUtil.FramingWriteOneCoil(1, 0, true);
            //            System.out.println(sendStr);
            //            pw.write(sendStr);
            //            pw.flush();
            ////        写单个保持寄存器
            //            sendStr = FrameUtil.FramingWriteOneHoldingRegister(1, 1000, 7834);
            //            System.out.println(sendStr);
            //            pw.write(sendStr);
            //            pw.flush();
            ////        写多个线圈
            //            sendStr = FrameUtil.FramingWriteCoils(1, 30001, 4, 0x0f);
            //            System.out.println(sendStr);
            //
            //            pw.write(sendStr);
            //            pw.flush();
            ////        写多个保持寄存器
            //            sendStr = FrameUtil.FramingWriteHoldingRegisters(1, 40001, 3, "00afd102e1ac");
            //            System.out.println(sendStr);
            //            pw.write(sendStr);
            //            pw.flush();
//            Scanner sc = new Scanner(System.in);
//
//            while (sc.hasNext()) {
//                sendStr = sc.nextLine();
//                pw.write(sendStr);
//                pw.flush();
//                System.out.println("客户端：" + sendStr);
//                //                quit终止连接
//                if ("quit".equals(sendStr)) {
//                    socket.shutdownOutput();
//                    os.close();
//                    pw.close();
//                    socket.close();
//                    break;
//                }
//            }
        } catch (IOException | NullPointerException e) {
            log.info("请求发送失败！");
//            e.printStackTrace();
        } finally {
//            pw.close();
//            System.out.println("pw关闭");
            //                os.close();
//                System.out.println("os关闭");
            //                socket.close();
//                System.out.println("socket关闭");
        }
    }
}
