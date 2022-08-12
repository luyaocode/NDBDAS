package com.asurplus.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysDict;
import com.asurplus.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典管理 前端控制器
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@RestController
@RequestMapping("/system/sys-dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @SaCheckPermission("system:dict:list")
    @GetMapping("list")
    public TableInfo list(SysDict sysDict) {
        return sysDictService.list(sysDict);
    }

    @GetMapping("listSelect")
    public RES listSelect() {
        return sysDictService.listSelect();
    }

    @SaCheckPermission("system:dict:query")
    @GetMapping("/{id}")
    public RES getSysDict(@PathVariable("id") Integer id) {
        return sysDictService.getSysDict(id);
    }

    @SysLog(title = "字典管理", type = BusinessType.INSERT)
    @SaCheckPermission("system:dict:add")
    @PostMapping("add")
    public RES add(@RequestBody SysDict sysDict) {
        return sysDictService.add(sysDict);
    }

    @SysLog(title = "字典管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:dict:edit")
    @PutMapping
    public RES update(@RequestBody SysDict sysDict) {
        return sysDictService.update(sysDict);
    }

    @SysLog(title = "字典管理", type = BusinessType.DELETE)
    @SaCheckPermission("system:dict:del")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable("ids") Integer[] ids) {
        return sysDictService.delete(ids);
    }

    @GetMapping("/optionselect")
    public RES optionselect() {
        return sysDictService.optionselect();
    }
}
