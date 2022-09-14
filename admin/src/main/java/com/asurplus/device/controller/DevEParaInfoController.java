package com.asurplus.device.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.device.entity.DevEParaInfo;
import com.asurplus.device.service.DevEParaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 电参量信息表 前端控制器
 * @author luyao
 * @since 2022/9/13
 */
@RestController
@RequestMapping("/device/dev-epara-info")
public class DevEParaInfoController {

    @Autowired
    private DevEParaInfoService devEParaInfoService;

    @SaCheckPermission("system:role:list")
    @GetMapping("A01-01")
    public List<DevEParaInfo> list(DevEParaInfo devEParaInfo) {
        System.out.println("运行了");
        List<DevEParaInfo> eParaList = devEParaInfoService.list();
        System.out.println("返回数据："+eParaList);
        return eParaList;
    }


}
