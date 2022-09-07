package com.asurplus.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.excel.ExportExcelUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysUserInfo;
import com.asurplus.system.service.SysUserInfoService;
import com.asurplus.system.vo.SysUserInfoAddVO;
import com.asurplus.system.vo.SysUserInfoVO;
import com.asurplus.system.vo.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lizhou
 * @since 2021-07-15
 */
@RestController
@RequestMapping("/system/sys-user-info")
public class SysUserInfoController {

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @SaCheckPermission("system:user:list")
    @GetMapping("list")
    public TableInfo list(SysUserInfo sysUserInfo) {
        return sysUserInfoService.list(sysUserInfo, false);
    }

    @SaCheckPermission("system:user:query")
    @GetMapping(value = {"/", "/{id}"})
    public RES getSysUserInfo(@PathVariable(value = "id", required = false) Integer id) {
        return sysUserInfoService.getSysUserInfo(id);
    }

    @SysLog(title = "用户管理", type = BusinessType.INSERT)
    @SaCheckPermission("system:user:add")
    @PostMapping
    public RES add(@RequestBody SysUserInfoAddVO sysUserInfoAddVO) {
        return sysUserInfoService.add(sysUserInfoAddVO);
    }

    @SysLog(title = "用户管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:user:edit")
    @PutMapping
    public RES update(@RequestBody SysUserInfoAddVO sysUserInfoAddVO) {
        return sysUserInfoService.update(sysUserInfoAddVO);
    }

    @SysLog(title = "用户管理", type = BusinessType.DELETE)
    @SaCheckPermission("system:user:del")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable("ids") Integer[] ids) {
        return sysUserInfoService.delete(ids);
    }

    @SysLog(title = "用户管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:user:edit")
    @PutMapping("updateStatus")
    public RES updateStatus(@RequestBody SysUserInfo sysUserInfo) {
        return sysUserInfoService.updateStatus(sysUserInfo);
    }

    @SysLog(title = "用户管理", type = BusinessType.EXPORT)
    @SaCheckPermission("system:user:export")
    @GetMapping(value = "/export")
    public void exportXls(SysUserInfo sysUserInfo) {
        List<?> list = sysUserInfoService.list(sysUserInfo, true).getRows();
        ExportExcelUtil.exportExcel(list, SysUserInfoVO.class, "用户信息表", "用户信息统计");
    }

    @SysLog(title = "用户管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:user:reset")
    @PutMapping("/resetPwd")
    public RES updateRestPwd(@RequestBody SysUserInfo sysUserInfo) {
        return sysUserInfoService.updateRestPwd(sysUserInfo);
    }

    @GetMapping("/authRole/{userId}")
    public RES listAuthRole(@PathVariable("userId") Integer userId) {
        return sysUserInfoService.listAuthRole(userId);
    }

    @SysLog(title = "用户管理", type = BusinessType.GRANT)
    @SaCheckPermission("system:user:edit")
    @PutMapping("/updateAuthRole")
    public RES updateAuthRole(Integer userId, Integer[] roleIds) {
        return sysUserInfoService.updateAuthRole(userId, roleIds);
    }
}
