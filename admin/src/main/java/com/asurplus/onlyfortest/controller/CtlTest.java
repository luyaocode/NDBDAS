package com.asurplus.onlyfortest.controller;

import com.asurplus.common.utils.RES;
import com.asurplus.mytcp.TcpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Socket;

import static com.asurplus.App.socketMap;

@RestController
public class CtlTest {
    private static final Logger log = LoggerFactory.getLogger(CtlTest.class);

    @GetMapping("/test/sendCtl")
    public RES sendCtl() {

        log.info("socketMap==" + socketMap);
        for (String k : socketMap.keySet()) {
            Socket socket = socketMap.get(k);
            if (socket != null && !TcpServer.isClientClose(socket)) {
                TcpServer.send(socket, "0001 0000 0006 01 03 9C5D 0001");
            }

        }
        return RES.ok();

    }

}
