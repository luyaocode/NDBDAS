package com.asurplus.monitor.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor/server")
public class ServerController {

    @Autowired
    private ServerService serverService;

    @SaCheckPermission("monitor:server:list")
    @GetMapping
    public RES list() {
        return serverService.getServerInfo();
    }
}
