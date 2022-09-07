package com.asurplus.monitor.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @SaCheckPermission("monitor:cache:list")
    @GetMapping
    public RES getCacheInfo() {
        return cacheService.getCacheInfo();
    }
}
