package com.asurplus.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysPermissionInfo;
import com.asurplus.system.service.SysPermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@RestController
@RequestMapping("/system/sys-permission-info")
public class SysPermissionInfoController {

    @Autowired
    private SysPermissionInfoService sysPermissionInfoService;

    @SaCheckPermission("system:menu:list")
    @GetMapping("list")
    public RES list(SysPermissionInfo sysPermissionInfo) {
        return RES.ok(sysPermissionInfoService.list(sysPermissionInfo));
    }

    @GetMapping("/{id}")
    public RES getSysPermissionInfo(@PathVariable("id") Integer id) {
        return sysPermissionInfoService.getSysPermissionInfo(id);
    }

    @SysLog(title = "菜单管理", type = BusinessType.INSERT)
    @SaCheckPermission("system:menu:add")
    @PostMapping
    public RES add(@RequestBody SysPermissionInfo sysPermissionInfo) {
        return sysPermissionInfoService.add(sysPermissionInfo);
    }

    @SysLog(title = "菜单管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:menu:edit")
    @PutMapping
    public RES update(@RequestBody SysPermissionInfo sysPermissionInfo) {
        return sysPermissionInfoService.update(sysPermissionInfo);
    }

    @SysLog(title = "菜单管理", type = BusinessType.DELETE)
    @SaCheckPermission("system:menu:del")
    @DeleteMapping("/{id}")
    public RES delete(@PathVariable("id") Integer id) {
        return sysPermissionInfoService.delete(id);
    }

    @GetMapping("treeSelect")
    public RES treeSelect(SysPermissionInfo sysPermissionInfo) {
        return sysPermissionInfoService.treeSelect(sysPermissionInfo);
    }

    @GetMapping("rolePermissionTreeSelect/{roleId}")
    public RES rolePermissionTreeSelect(@PathVariable("roleId") Integer roleId) {
        return sysPermissionInfoService.rolePermissionTreeSelect(roleId);
    }
}
