package com.asurplus.gateway.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.service.GatewayService;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.asurplus.common.utils.RES;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    @SaCheckPermission("system:config:list")
    @GetMapping("list")
    public TableInfo list(GatewayInfo gatewayInfo){
        TableInfo list;
        list = gatewayService.list(gatewayInfo);
        System.out.println("返回数据："+list);
        return list;
    }
    @SaCheckPermission("system:config:list")
    @GetMapping("{id}")
    public RES getById(@PathVariable Integer id){
        RES ok = gatewayService.getById(id);
        System.out.println(ok);
        return ok;
    }

    @SysLog(title = "网关配置", type = BusinessType.INSERT)
    @SaCheckPermission("system:config:add")
    @PostMapping
    public RES add(@RequestBody GatewayInfo gatewayInfo) {
        return gatewayService.add(gatewayInfo);
    }

    @SysLog(title = "网关配置", type = BusinessType.DELETE)
    @SaCheckPermission("system:config:remove")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable("ids") Integer[] ids) {
        return gatewayService.delete(ids);
    }

    @SysLog(title = "网关配置", type = BusinessType.UPDATE)
    @SaCheckPermission("system:config:edit")
    @PutMapping
    public RES update(@RequestBody GatewayInfo gatewayInfo) {
        return gatewayService.update(gatewayInfo);
    }
}