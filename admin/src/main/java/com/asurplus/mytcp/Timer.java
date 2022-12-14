package com.asurplus.mytcp;


import com.asurplus.device.entity.DevInfo;
import com.asurplus.device.service.DevInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.List;

import static com.asurplus.App.socketMap;

/**
 * 定时器。定时向所有网关发送请求，请求所有电参量
 *
 * @author chenluyao
 */
@Component
public class Timer {
    private final Logger log = LoggerFactory.getLogger(Timer.class);

    @Autowired
    private DevInfoService devInfoService;

    @SneakyThrows
    @Async("taskExecutor")
    public void requestEPara() {
        log.info("开启请求电参量线程");
        while (true) {
            if (socketMap.size() > 0) {
                for (String k : socketMap.keySet()) {
                    //        socket的addr
                    Socket socket = socketMap.get(k);
//                        找到当下网关所有设备
                    if (socket != null) {
                        String ip = socket.getInetAddress().getHostAddress();
                        Integer port = socket.getPort();
                        QueryWrapper<DevInfo> qw = new QueryWrapper<>();
                        qw.eq("ip", ip).eq("port", port).orderByAsc("sort");
                        List<DevInfo> list = null;
                        list = devInfoService.list(qw);
                        if (list != null) {

                            for (DevInfo info : list) {
                                //                            组帧
                                String sendStr = FrameUtil.FramingRead(3, info.getSort(), 40004, 1);
                                //                            发送请求帧
                                TcpServer.send(socket, sendStr);
                                //                            间隔时间
                                Thread.sleep(10000);
                            }
                        }
                    }
                }
            }
//            每？秒发送一次请求帧
            Thread.sleep(10000);
        }
    }

}
