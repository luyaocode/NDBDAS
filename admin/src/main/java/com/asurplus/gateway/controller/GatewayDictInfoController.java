package com.asurplus.gateway.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.utils.RES;
import com.asurplus.gateway.entity.GatewayDictInfo;
import com.asurplus.gateway.service.GatewayDictInfoService;
import com.asurplus.gateway.service.impl.GatewayInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luyao
 */
@RestController
public class GatewayDictInfoController {

    @Autowired
    private GatewayDictInfoService gatewayDictInfoService;

    private static final Logger log = LoggerFactory.getLogger(GatewayInfoServiceImpl.class);

    @SaCheckPermission("system:role:list")
    @GetMapping("/config/gateway/dictList/{dictType}")
    public RES list(@PathVariable("dictType") String dictType){
        List<GatewayDictInfo> list = gatewayDictInfoService.list(dictType);
        return RES.ok(list);
    }
}
