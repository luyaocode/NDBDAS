package com.asurplus.config.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.excel.ExportExcelUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.config.entity.SysApp;
import com.asurplus.config.service.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * app版本信息Controller
 *
 * @author lizhou
 * @date 2021-07-26
 */
@RestController
@RequestMapping("/config/sys-app")
public class SysAppController {

    @Autowired
    private SysAppService sysAppService;

    /**
     * 查询app版本信息列表
     */
    @SaCheckPermission("config:app:list")
    @GetMapping("/list")
    public TableInfo list(SysApp sysApp) {
        return sysAppService.list(sysApp, 0);
    }

    /**
     * 获取app版本信息详细信息
     */
    @SaCheckPermission("config:app:query")
    @GetMapping(value = "/{id}")
    public RES getSysApp(@PathVariable("id") Long id) {
        return sysAppService.getSysApp(id);
    }

    /**
     * 新增app版本信息
     */
    @SysLog(title = "APP版本", type = BusinessType.INSERT)
    @SaCheckPermission("config:app:add")
    @PostMapping
    public RES add(@RequestBody SysApp sysApp) {
        return sysAppService.add(sysApp);
    }

    /**
     * 修改app版本信息
     */
    @SysLog(title = "APP版本", type = BusinessType.UPDATE)
    @SaCheckPermission("config:app:edit")
    @PutMapping
    public RES edit(@RequestBody SysApp sysApp) {
        return sysAppService.update(sysApp);
    }

    /**
     * 删除app版本信息
     */
    @SysLog(title = "APP版本", type = BusinessType.DELETE)
    @SaCheckPermission("config:app:del")
    @DeleteMapping("/{ids}")
    public RES remove(@PathVariable Long[] ids) {
        return sysAppService.delete(ids);
    }

    /**
     * 导出app版本信息列表
     */
    @SysLog(title = "APP版本", type = BusinessType.EXPORT)
    @SaCheckPermission("config:app:export")
    @GetMapping("/export")
    public void export(SysApp sysApp) {
        List<?> list = sysAppService.list(sysApp, 1).getRows();
        ExportExcelUtil.exportExcel(list, SysApp.class, "app版本信息表", "app版本信息统计");
    }
}
