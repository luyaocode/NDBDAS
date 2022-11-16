package com.asurplus.mytcp;

import com.asurplus.myutil.SqlUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;

import static com.asurplus.App.socketMap;

/**
 * @author chenluyao
 * 使用心跳检测，管理网关的连接状态，并与前端显示数据同步
 */
public class HeartBeatDetectionClient extends Thread {
    private static final Logger log = LoggerFactory.getLogger(HeartBeatDetectionClient.class);

    @SneakyThrows
    @Override
    public void run() {
        int count, connectedNum;
        Thread.sleep(20000);
        log.info("开启客户端心跳检测线程");
        while (true) {
            connectedNum = 0;
            if (socketMap.size() > 0) {
                for (String addr : socketMap.keySet()) {
                    count = 3;
                    String[] split = addr.split(":");
                    String desIp = split[0];
                    String desPort = split[1];
                    Socket socket = socketMap.get(addr);
                    String querySql = "SELECT status from gateway_info WHERE ip='" + desIp + "' AND port='" + desPort + "'";
                    int status = SqlUtil.executeQuery(querySql);
//                    log.info("查到status=="+status);
                    if (socket != null) {
                        connectedNum++;
                        //                    检测发送数据是否异常
                        while (count-- >= 0) {
                            if (TcpConnection.isServerClose(socket) || socket.isClosed() || !socket.isConnected()) {
                                log.info("网关状态异常，请等一会儿" + socket);
                                Thread.sleep(5000);

                            } else {
                                log.info("太好了，网关状态正常" + socket);
                                break;
                            }
                        }
                        if (count < 0) {
                            if (status == 0) {
                                //                    更新网关状态,并将相应的socket置为null
                                log.info("当前网关状态为已连接，需要设置为未连接" + socket);
                                String sql = "UPDATE gateway_info SET status='1' WHERE ip='" + desIp + "' AND port='" + desPort + "'";
                                SqlUtil.executeUpdate(sql);
                                socket.close();
                                socketMap.replace(addr, socket, null);
                            }
                        } else {
                            if (status == 1) {
                                //                    更新网关状态
                                log.info("当前网关状态为未连接，需要设置为已连接" + socket);
                                String sql = "UPDATE gateway_info SET status='0' WHERE ip='" + desIp + "' AND port='" + desPort + "'";
                                SqlUtil.executeUpdate(sql);
                            }
                        }
                    }
                }
            }
            log.info("当前已连接的网关个数为:" + connectedNum);
            Thread.sleep(10000);
        }
    }
}
