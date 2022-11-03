package com.asurplus.device.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.excel.ExportExcelUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.device.entity.DevLocInfo;
import com.asurplus.device.service.DevLocInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DevLocInfo)表控制层
 *
 * @author makejava
 * @since 2022-10-19 15:52:30
 */
@RestController
@RequestMapping("/device/location")
public class DevLocInfoController {
    /**
     * 服务对象
     */
    @Resource
    private DevLocInfoService devLocInfoService;

    @SaCheckPermission("system:user:list")
    @GetMapping("list")
    public RES list(DevLocInfo devLocInfo){
        RES res = RES.ok(devLocInfoService.list(devLocInfo));
        System.out.println("获取地理位置信息："+res);
        return res;
    }

//    主要
    @SaCheckPermission("system:user:list")
    @GetMapping("treeSelect")
    public RES treeSelect(DevLocInfo devLocInfo){
        RES res = devLocInfoService.treeSelect(devLocInfo);
//        System.out.println("获取楼层树："+res);
        return res;
    }

    /**
     *
     */
    @SaCheckPermission("system:user:list")
    @GetMapping("/export")
    public void exportXls(DevLocInfo devLocInfo) {
        List<?> list = devLocInfoService.list(devLocInfo);
        ExportExcelUtil.exportExcel(list, DevLocInfo.class, "楼层编号对照表", "楼层编号对照表");
    }

}

