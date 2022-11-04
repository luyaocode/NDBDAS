package com.asurplus.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysRoleInfo;
import com.asurplus.system.entity.SysUserRole;
import com.asurplus.system.service.SysRoleInfoService;
import com.asurplus.system.vo.SysRoleInfoAddVO;
import com.asurplus.system.vo.SysUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *  @SaCheckPermission
 * @author lizhou
 * @since 2021-07-15
 */
@RestController
@RequestMapping("/system/sys-role-info")
public class SysRoleInfoController {

    @Autowired
    private SysRoleInfoService sysRoleInfoService;

    @SaCheckPermission("system:role:list")
    @GetMapping("list")
    public TableInfo list(SysRoleInfo sysRoleInfo) {
        return sysRoleInfoService.list(sysRoleInfo);
    }

    @SaCheckPermission("system:role:query")
    @GetMapping("/{id}")
    public RES getSysRoleInfo(@PathVariable("id") Integer id) {
        return sysRoleInfoService.getSysRoleInfo(id);
    }

    @SysLog(title = "角色管理", type = BusinessType.INSERT)
    @SaCheckPermission("system:role:add")
    @PostMapping
    public RES add(@RequestBody SysRoleInfoAddVO sysRoleInfoAddVO) {
        return sysRoleInfoService.add(sysRoleInfoAddVO);
    }

    @SysLog(title = "角色管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:role:edit")
    @PutMapping
    public RES update(@RequestBody SysRoleInfoAddVO sysRoleInfoAddVO) {
        return sysRoleInfoService.update(sysRoleInfoAddVO);
    }

    @SysLog(title = "角色管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:role:edit")
    @PutMapping("updateStatus")
    public RES updateStatus(@RequestBody SysRoleInfo sysRoleInfo) {
        return sysRoleInfoService.updateStatus(sysRoleInfo);
    }

    @SysLog(title = "角色管理", type = BusinessType.DELETE)
    @SaCheckPermission("system:role:del")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable("ids") Integer[] ids) {
        return sysRoleInfoService.delete(ids);
    }

    /**
     * 查询已经授权的用户信息
     *
     * @param sysUserInfo
     * @return
     */
    @GetMapping("allocatedList")
    public TableInfo allocatedList(SysUserInfoVO sysUserInfo) {
        return sysRoleInfoService.allocatedList(sysUserInfo);
    }

    @GetMapping("unAllocatedList")
    public TableInfo unAllocatedList(SysUserInfoVO sysUserInfo) {
        return sysRoleInfoService.unAllocatedList(sysUserInfo);
    }

    @SysLog(title = "角色管理", type = BusinessType.GRANT)
    @PutMapping("addAuthAll")
    public RES addAuthAll(Integer roleId, Integer[] ids) {
        return sysRoleInfoService.addAuthAll(roleId, ids);
    }

    @SysLog(title = "角色管理", type = BusinessType.GRANT)
    @PutMapping("cancelAuth")
    public RES updateCancelAuth(@RequestBody SysUserRole sysUserRole) {
        return sysRoleInfoService.updateCancelAuth(sysUserRole);
    }

    @SysLog(title = "角色管理", type = BusinessType.GRANT)
    @PutMapping("cancelAuthAll")
    public RES updateCancelAuthAll(Long roleId, Long[] ids) {
        return sysRoleInfoService.updateCancelAuthAll(roleId, ids);
    }
}
