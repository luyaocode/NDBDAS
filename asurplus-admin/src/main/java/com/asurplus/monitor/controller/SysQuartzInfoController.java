package com.asurplus.monitor.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.excel.ExportExcelUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.entity.SysQuartzInfo;
import com.asurplus.monitor.service.SysQuartzInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定时任务信息Controller
 *
 * @author lizhou
 * @date 2021-07-22
 */
@RestController
@RequestMapping("/monitor/sys-quartz-info")
public class SysQuartzInfoController {

    @Autowired
    private SysQuartzInfoService sysQuartzInfoService;

    /**
     * 查询定时任务信息列表
     */
    @SaCheckPermission("monitor:job:list")
    @GetMapping("/list")
    public TableInfo list(SysQuartzInfo sysQuartzInfo) {
        return sysQuartzInfoService.list(sysQuartzInfo);
    }

    /**
     * 获取定时任务信息详细信息
     */
    @SaCheckPermission("monitor:job:query")
    @GetMapping(value = "/{id}")
    public RES getSysQuartzInfo(@PathVariable("id") Integer id) {
        return sysQuartzInfoService.getSysQuartzInfo(id);
    }

    /**
     * 新增定时任务信息
     */
    @SysLog(title = "定时任务", type = BusinessType.INSERT)
    @SaCheckPermission("monitor:job:add")
    @PostMapping
    public RES add(@RequestBody SysQuartzInfo sysQuartzInfo) {
        return sysQuartzInfoService.add(sysQuartzInfo);
    }

    /**
     * 修改定时任务信息
     */
    @SysLog(title = "定时任务", type = BusinessType.UPDATE)
    @SaCheckPermission("monitor:job:edit")
    @PutMapping
    public RES edit(@RequestBody SysQuartzInfo sysQuartzInfo) {
        return sysQuartzInfoService.update(sysQuartzInfo);
    }

    /**
     * 启停定时任务
     */
    @SysLog(title = "定时任务", type = BusinessType.UPDATE)
    @SaCheckPermission("monitor:job:status")
    @PutMapping("updateStatus")
    public RES updateStatus(@RequestBody SysQuartzInfo sysQuartzInfo) {
        return sysQuartzInfoService.updateStatus(sysQuartzInfo);
    }

    /**
     * 立即执行定时任务
     */
    @SysLog(title = "定时任务", type = BusinessType.UPDATE)
    @SaCheckPermission("monitor:job:status")
    @PutMapping("run")
    public RES run(@RequestBody SysQuartzInfo sysQuartzInfo) {
        return sysQuartzInfoService.run(sysQuartzInfo);
    }

    /**
     * 删除定时任务信息
     */
    @SysLog(title = "定时任务", type = BusinessType.DELETE)
    @SaCheckPermission("monitor:job:del")
    @DeleteMapping("/{ids}")
    public RES remove(@PathVariable Integer[] ids) {
        return sysQuartzInfoService.delete(ids);
    }

    /**
     * 导出定时任务信息列表
     */
    @SysLog(title = "定时任务", type = BusinessType.EXPORT)
    @SaCheckPermission("monitor:job:export")
    @GetMapping("/export")
    public void export(SysQuartzInfo sysQuartzInfo) {
        List<?> list = sysQuartzInfoService.list(sysQuartzInfo).getRows();
        ExportExcelUtil.exportExcel(list, SysQuartzInfo.class, "定时任务信息表", "定时任务信息统计");
    }
}
