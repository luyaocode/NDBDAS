package com.asurplus.mytcp;

import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.service.GatewayInfoService;
import com.asurplus.myutil.SpringUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static com.asurplus.App.socketMap;

/**
 * @author chenluyao
 * 网关服务端程序
 */
@Component
public class TcpServer {
    private static final Logger log = LoggerFactory.getLogger(TcpServer.class);
    /**
     * 开启socket连接
     */
    @Autowired
    private GatewayInfoService gatewayInfoService;

    @SneakyThrows
    @Async("taskExecutor")
    public void accept() {
//        初始化网关信息表：所有设为2-未知状态
        gatewayInfoService.setAllStatus(2);
        // 服务端监听 9999 端口
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            Socket socket = serverSocket.accept();
            log.info("连接成功！" + socket);
            String ip = socket.getInetAddress().getHostAddress();
            String port = String.valueOf(socket.getPort());
            String addr = ip + ":" + port;
//            将socket保存到哈希表
            socketMap.put(addr, socket);
//            将网关保存到数据库
            GatewayInfo gatewayInfo = new GatewayInfo();
            gatewayInfo.setIp(ip);
            gatewayInfo.setPort(socket.getPort());
//            0-已连接
            gatewayInfo.setStatus(0);
//            1-非内置
            gatewayInfo.setType(1);
            gatewayInfoService.add(gatewayInfo);
//            调用接收线程
            TcpReceive tcpReceive = SpringUtil.getBean(TcpReceive.class);
            tcpReceive.receive(socket);
//            请求设备地址及编号
            String sendStr=FrameUtil.FramingRead(3,0,40029,1);
            TcpServer.send(socket,sendStr);
        }

    }
    /**
     * 发送单条指令
     */
    @SneakyThrows
    public static void send(Socket socket, String sendStr) {
        if(null==socket){
            log.info("socket is null");
            return ;
        }
        OutputStream os = null;
        PrintWriter pw = null;
        // 要发送给服务器的信息
        try {
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            //           一、测试发送帧
//            0102-读
            if (sendStr == null) {
                sendStr = FrameUtil.FramingRead(1, 1, 0, 74);
            }
            log.info("请求帧：" + sendStr+socket);
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
        } catch (IOException ex) {
            log.info("请求发送异常！");
        }
//            任何流的关闭都将导致socket的关闭
//            if (os != null) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
    }

    /**
     * 判断客户端否断开连接
     */
    public static boolean isClientClose(Socket socket){
        try{
            //发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            socket.sendUrgentData(0xFF);
            return false;
        }catch(Exception se){
            log.info("客户端已经断开连接"+socket);
            return true;
        }
    }
}
