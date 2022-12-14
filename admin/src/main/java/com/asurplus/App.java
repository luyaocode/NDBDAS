package com.asurplus;

import com.asurplus.mytcp.HeartBeatDetectionClient;
import com.asurplus.mytcp.TcpServer;
import com.asurplus.mytcp.Timer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

/**
 * 项目启动类
 *
 * @Author Luyao
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//不配置@MapperScan也能运行
//@MapperScan("com.asurplus.device.mapper")
//@MapperScan("com.asurplus.system.mapper")
/**使支持异步任务，只能加在启动类？*/
@EnableAsync
public class App {

//    public static Object testObj;
    /**
     * 定义一个全局变量，哈希表< 主键,socket对象>形式存放socket对象
     */
    public static Map<String, Socket> socketMap = new HashMap<>();
    /**
     * < rand,funCode>
     */
    public static Map<String, Integer> randMap = new HashMap<>();


    @SneakyThrows
    public static void main(String[] args) {
        /** 启动springboot*/
        ConfigurableApplicationContext application = SpringApplication.run(App.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Declare is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "\n\t" +
                "Swagger-UI: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
        /**
         * 启动写数据库线程
         * 接收tcp协议数据-写数据库
         */
//        application.getBean(RunnerLoadOne.class);
//        List<String> addrList = RunnerLoadOne.getAddrList();
//        log.info("数据库网关个数为"+addrList.size());
//        new HeartBeatDetectionClient().start();
//        for (String addr : addrList) {
//            String[] split = addr.split(":");
//            String desIp = split[0];
//            Integer desPort = Integer.valueOf(split[1]);
//            TcpConnection tcpConnection = new TcpConnection();
//            Future<Socket> futureSocket = tcpConnection.connect(desIp, desPort);
//            //get()方法阻塞等待异步线程返回结果
//            Socket socket = futureSocket.get();
//            if (socket != null) {
////                将socket放到哈希表
//                socketMap.put(addr, socket);
////                更新网关状态
//                SqlUtil.executeUpdate("UPDATE gateway_info SET status='0' WHERE ip='"+desIp+"' AND port='"+desPort+"'");
////             开启数据接收线程
////                tcpConnection.receive(socket);//弃用
//                new ThreadReceive(socket).start();
//            }else{
//                socketMap.put(addr, null);
//                SqlUtil.executeUpdate("UPDATE gateway_info SET status='1' WHERE ip='"+desIp+"' AND port='"+desPort+"'");
//            }
//        }
//        一个线程负责连接网关；N个线程负责开启receive
        TcpServer tcpServer = application.getBean(TcpServer.class);
        tcpServer.accept();
//        开启心跳检测线程
        HeartBeatDetectionClient heartBeatDetectionClient = application.getBean(HeartBeatDetectionClient.class);
        heartBeatDetectionClient.heartBeat();
//        开启定时器，发送请求帧
        Timer timer = application.getBean(Timer.class);
        timer.requestEPara();

    }
}
