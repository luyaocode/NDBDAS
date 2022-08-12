package com.asurplus.monitor.service.impl;

import cn.hutool.core.date.DateUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.service.ServerService;
import com.asurplus.monitor.utils.ServerUtils;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.Date;

@Service
public class ServerServiceImpl implements ServerService {

    @Override
    public RES getServerInfo() {
        RES res = RES.ok();
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        HardwareAbstractionLayer hal = si.getHardware();
        // 系统信息
        res.put("sys", ServerUtils.getSystemInfo(os));
        // cpu 信息
        res.put("cpu", ServerUtils.getCpuInfo(hal.getProcessor()));
        // 内存信息
        res.put("memory", ServerUtils.getMemoryInfo(hal.getMemory()));
        // 交换区信息
        res.put("swap", ServerUtils.getSwapInfo(hal.getMemory()));
        // 磁盘
        res.put("disk", ServerUtils.getDiskInfo(os));
        // 当前时间
        res.put("time", DateUtil.format(new Date(), "HH:mm:ss"));
        return res;
    }
}
