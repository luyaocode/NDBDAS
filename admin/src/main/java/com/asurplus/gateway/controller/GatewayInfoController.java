package com.asurplus.gateway.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.service.GatewayService;
import com.asurplus.system.vo.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config/gateway")
public class GatewayInfoController {
    @Autowired
    private GatewayService gatewayService;


    /**
     * 获取网关列表-分页形式
     * @param gatewayInfo
     * @return
     */
    @SaCheckPermission("system:role:list")
    @GetMapping("list")
    public TableInfo list(GatewayInfo gatewayInfo){
        TableInfo list;
        list = gatewayService.list(gatewayInfo);
        System.out.println("返回数据："+list);
        return list;
    }

}
