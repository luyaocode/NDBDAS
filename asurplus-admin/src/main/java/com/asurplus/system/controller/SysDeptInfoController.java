package com.asurplus.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysDeptInfo;
import com.asurplus.system.service.SysDeptInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门信息表 前端控制器
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@RestController
@RequestMapping("/system/sys-dept-info")
public class SysDeptInfoController {

    @Autowired
    private SysDeptInfoService sysDeptInfoService;

    @SaCheckPermission("system:dept:list")
    @GetMapping("list")
    public RES list(SysDeptInfo sysDeptInfo) {
        return RES.ok(sysDeptInfoService.list(sysDeptInfo));
    }

    @SaCheckPermission("system:dept:query")
    @GetMapping("/{id}")
    public RES getSysDeptInfo(@PathVariable("id") Integer id) {
        return sysDeptInfoService.getSysDeptInfo(id);
    }

    @SysLog(title = "部门管理", type = BusinessType.INSERT)
    @SaCheckPermission("system:dept:add")
    @PostMapping
    public RES add(@RequestBody SysDeptInfo sysDeptInfo) {
        return sysDeptInfoService.add(sysDeptInfo);
    }

    @SysLog(title = "部门管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:dept:edit")
    @PutMapping
    public RES update(@RequestBody SysDeptInfo sysDeptInfo) {
        return sysDeptInfoService.update(sysDeptInfo);
    }

    @SysLog(title = "部门管理", type = BusinessType.DELETE)
    @SaCheckPermission("system:dept:del")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable("ids") Integer[] ids) {
        return sysDeptInfoService.delete(ids);
    }

    @GetMapping("treeSelect")
    public RES treeSelect(SysDeptInfo sysDeptInfo) {
        return sysDeptInfoService.treeSelect(sysDeptInfo);
    }

    @GetMapping("/exclude/{deptId}")
    public RES excludeChild(@PathVariable(value = "deptId", required = false) Integer deptId) {
        SysDeptInfo sysDeptInfo = new SysDeptInfo();
        sysDeptInfo.setId(deptId);
        return RES.ok(sysDeptInfoService.list(sysDeptInfo));
    }
}
