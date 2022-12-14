package com.asurplus.gateway.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.utils.RES;
import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.service.GatewayDictInfoService;
import com.asurplus.gateway.service.GatewayInfoService;
import com.asurplus.system.vo.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway")
public class GatewayInfoController {
    @Autowired
    private GatewayInfoService gatewayInfoService;
    @Autowired
    private GatewayDictInfoService gatewayDictInfoService;
    private GatewayInfo gatewayInfo;

    /**
     * 获取网关列表-分页形式
     *
     * @param gatewayInfo
     * @return
     */

    @SaCheckPermission("system:role:list")
    @GetMapping("/list")
    public TableInfo list(GatewayInfo gatewayInfo) {
        TableInfo list;
        list = gatewayInfoService.list(gatewayInfo);
        System.out.println("返回数据：" + list);
        return list;
    }

    /**
     * 获取网关列表-不分页形式
     * @return RES
     */
    @SaCheckPermission("system:role:list")
    @GetMapping("/listGateway")
    public RES list() {
        return gatewayInfoService.listGateway();
    }

    @PostMapping
    public RES add(@RequestBody GatewayInfo gatewayInfo){
        System.out.println(gatewayInfo);
        return gatewayInfoService.add(gatewayInfo);
    }
    /**
     * 根据id获取单个网关
     * @param id
     * @return
     */

    @SaCheckPermission("system:config:list")
    @GetMapping("/{id}")
    public RES getById(@PathVariable Integer id) {
        RES ok = gatewayInfoService.getById(id);
        System.out.println(ok);
        return ok;
    }

//    @SaCheckPermission("system:config:list")
//    @GetMapping("/connect/{id}")
//    public RES connect(@PathVariable("id") Integer id) throws ExecutionException, InterruptedException {
//        return gatewayInfoService.connect(id);
//    }

    @SaCheckPermission("system:config:list")
    @PutMapping("/updateStatus")
    public RES updateStatus(@RequestBody GatewayInfo gatewayInfo){

        return gatewayInfoService.updateStatus(gatewayInfo);
    }

//    @SysLog(title = "网关配置", type = BusinessType.INSERT)
//    @SaCheckPermission("system:config:add")
//    @PostMapping
//    public RES add(@RequestBody GatewayInfo gatewayInfo) {
//        return gatewayInfoService.add(gatewayInfo);
//    }

    @SysLog(title = "网关配置", type = BusinessType.DELETE)
    @SaCheckPermission("system:config:remove")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable("ids") Integer[] ids) {
        return gatewayInfoService.delete(ids);
    }

    @SysLog(title = "网关配置", type = BusinessType.UPDATE)
    @SaCheckPermission("system:config:edit")
    @PutMapping
    public RES update(@RequestBody GatewayInfo gatewayInfo) {
        return gatewayInfoService.update(gatewayInfo);
    }
}

