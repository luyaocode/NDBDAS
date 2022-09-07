package com.asurplus.monitor.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.excel.ExportExcelUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.entity.SysLoginLog;
import com.asurplus.monitor.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 登录日志 前端控制器
 * </p>
 *
 * @author lizhou
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/monitor/sys-login-log")
public class SysLoginLogController {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @SaCheckPermission("monitor:logininfor:list")
    @GetMapping("list")
    public TableInfo list(SysLoginLog sysLoginLog) {
        return sysLoginLogService.list(sysLoginLog, false);
    }

    @SysLog(title = "登录日志", type = BusinessType.DELETE)
    @SaCheckPermission("monitor:logininfor:del")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable("ids") Integer[] ids) {
        return sysLoginLogService.delete(ids);
    }

    @SysLog(title = "登录日志", type = BusinessType.CLEAN)
    @SaCheckPermission("monitor:logininfor:clear")
    @DeleteMapping("/clean")
    public RES deleteAll() {
        return sysLoginLogService.deleteAll();
    }

    @SysLog(title = "登录日志", type = BusinessType.EXPORT)
    @SaCheckPermission("monitor:logininfor:export")
    @GetMapping(value = "/export")
    public void exportXls(SysLoginLog sysLoginLog) {
        List<?> list = sysLoginLogService.list(sysLoginLog, true).getRows();
        ExportExcelUtil.exportExcel(list, SysLoginLog.class, "登录日志表", "登录日志统计");
    }
}
