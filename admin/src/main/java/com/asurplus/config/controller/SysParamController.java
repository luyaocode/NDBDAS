package com.asurplus.config.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.excel.ExportExcelUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.config.entity.SysParam;
import com.asurplus.config.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统参数配置Controller
 *
 * @author lizhou
 * @date 2021-07-26
 */
@RestController
@RequestMapping("/config/sys-param")
public class SysParamController {

    @Autowired
    private SysParamService sysParamService;

    /**
     * 查询系统参数配置列表
     */
    @SaCheckPermission("config:param:list")
    @GetMapping("/list")
    public TableInfo list(SysParam sysParam) {
        return sysParamService.list(sysParam);
    }

    /**
     * 获取系统参数配置详细信息
     */
    @SaCheckPermission("config:param:query")
    @GetMapping(value = "/{id}")
    public RES getSysParam(@PathVariable("id") Integer id) {
        return sysParamService.getSysParam(id);
    }

    /**
     * 新增系统参数配置
     */
    @SysLog(title = "参数配置", type = BusinessType.INSERT)
    @SaCheckPermission("config:param:add")
    @PostMapping
    public RES add(@RequestBody SysParam sysParam) {
        return sysParamService.add(sysParam);
    }

    /**
     * 修改系统参数配置
     */
    @SysLog(title = "参数配置", type = BusinessType.UPDATE)
    @SaCheckPermission("config:param:edit")
    @PutMapping
    public RES edit(@RequestBody SysParam sysParam) {
        return sysParamService.update(sysParam);
    }

    /**
     * 删除系统参数配置
     */
    @SysLog(title = "参数配置", type = BusinessType.DELETE)
    @SaCheckPermission("config:param:del")
    @DeleteMapping("/{ids}")
    public RES remove(@PathVariable Integer[] ids) {
        return sysParamService.delete(ids);
    }

    /**
     * 根据参数键，获取参数值
     *
     * @param key
     * @return
     */
    @GetMapping(value = "/getParamValue/{key}")
    public RES getParamValue(@PathVariable("key") String key) {
        return RES.ok(sysParamService.getParamValue(key));
    }

    /**
     * 导出系统参数配置列表
     */
    @SysLog(title = "参数配置", type = BusinessType.EXPORT)
    @SaCheckPermission("config:param:export")
    @GetMapping("/export")
    public void export(SysParam sysParam) {
        List<?> list = sysParamService.list(sysParam).getRows();
        ExportExcelUtil.exportExcel(list, SysParam.class, "系统参数配置表", "系统参数配置统计");
    }
}
