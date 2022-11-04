package com.asurplus;

import com.asurplus.gateway.util.RunnerLoadOne;
import com.asurplus.mytcp.TcpConnection;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * 项目启动类
 *
 * @Author Luyao
 */
@Slf4j
@SpringBootApplication
//不配置@MapperScan也能运行
//@MapperScan("com.asurplus.device.mapper")
//@MapperScan("com.asurplus.system.mapper")
//使支持异步任务
@EnableAsync
public class App {

//    public static Object testObj;
//    定义一个全局变量，数组形式存放socket对象
    public static Socket[] socks =new Socket[1024];

    @SneakyThrows
    public static void main(String[] args) throws UnknownHostException {
        /**
         * 启动springboot
         */
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

        RunnerLoadOne runnerLoadOne = application.getBean(RunnerLoadOne.class);
        Map<String, Integer> urlMap = RunnerLoadOne.getUrlMap();
        Set<String> keys = urlMap.keySet();
        int gateways = keys.size();
        int i=0;
        System.out.println(gateways);
        for (String key:keys){
            System.out.println(key+":"+urlMap.get(key));
        }
        for (String key:keys){
//            System.out.println("[ip:"+key+",port:"+urlMap.get(key)+"]");
            TcpConnection tcpConnection = application.getBean(TcpConnection.class);
            Future<Socket> futureSocket = tcpConnection.connect(key, urlMap.get(key));
//            System.out.println("[futureSocket:]"+futureSocket);
            Socket socket = futureSocket.get();//get()方法阻塞等待异步线程返回结果
//            System.out.println("接收异步方法返回结果");
//            System.out.println(socket);
            if(socket==null){
                System.out.println("异步方法调用错误！");
            }else{
//                保存socket到数组
                socks[i] = socket;
//                System.out.println("socket["+i+"]:"+socks[i]);
                i++;
//             开启数据接收线程
                tcpConnection.receive(socket);
            }
//            tcpConn.send();
//            tcpConn.receive();
        }
//        testObj=new Object();
//        System.out.println("[main]"+testObj);

    }
}
