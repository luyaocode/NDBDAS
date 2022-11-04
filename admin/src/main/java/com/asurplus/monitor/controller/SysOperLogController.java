package com.asurplus.monitor.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.excel.ExportExcelUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.entity.SysOperLog;
import com.asurplus.monitor.service.SysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录Controller
 *
 * @author lizhou
 * @date 2021-07-26
 */
@RestController
@RequestMapping("/monitor/sys-oper-log")
public class SysOperLogController {

    @Autowired
    private SysOperLogService sysOperLogService;

    /**
     * 查询操作日志记录列表
     */
    @SaCheckPermission("monitor:oper:list")
    @GetMapping("/list")
    public TableInfo list(SysOperLog sysOperLog) {
        return sysOperLogService.list(sysOperLog);
    }

    /**
     * 删除操作日志记录
     */
    @SysLog(title = "操作日志", type = BusinessType.DELETE)
    @SaCheckPermission("monitor:oper:del")
    @DeleteMapping("/{ids}")
    public RES remove(@PathVariable Long[] ids) {
        return sysOperLogService.delete(ids);
    }

    /**
     * 清空操作日志记录
     *
     * @return
     */
    @SysLog(title = "操作日志", type = BusinessType.CLEAN)
    @SaCheckPermission("monitor:oper:clear")
    @DeleteMapping("/clean")
    public RES deleteAll() {
        return sysOperLogService.deleteAll();
    }

    /**
     * 导出操作日志记录列表
     */
    @SysLog(title = "操作日志", type = BusinessType.EXPORT)
    @SaCheckPermission("monitor:oper:export")
    @GetMapping("/export")
    public void export(SysOperLog sysOperLog) {
        List<?> list = sysOperLogService.list(sysOperLog).getRows();
        ExportExcelUtil.exportExcel(list, SysOperLog.class, "操作日志记录表", "操作日志记录统计");
    }
}
