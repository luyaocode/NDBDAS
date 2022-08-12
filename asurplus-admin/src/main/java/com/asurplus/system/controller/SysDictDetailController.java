package com.asurplus.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysDictDetail;
import com.asurplus.system.service.SysDictDetailService;
import com.asurplus.system.vo.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典配置 前端控制器
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@RestController
@RequestMapping("/system/sys-dict-detail")
public class SysDictDetailController {

    @Autowired
    private SysDictDetailService sysDictDetailService;

    @SaCheckPermission("system:dict:list")
    @GetMapping("list")
    public TableInfo list(SysDictDetail sysDictDetail) {
        return sysDictDetailService.list(sysDictDetail);
    }

    @SaCheckPermission("system:dict:query")
    @GetMapping("/{id}")
    public RES getSysDictDetail(@PathVariable("id") Integer id) {
        return sysDictDetailService.getSysDictDetail(id);
    }

    @SysLog(title = "字典管理", type = BusinessType.INSERT)
    @SaCheckPermission("system:dict:add")
    @PostMapping
    public RES add(@RequestBody SysDictDetail sysDictDetail) {
        return sysDictDetailService.add(sysDictDetail);
    }

    @SysLog(title = "字典管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:dict:edit")
    @PutMapping
    public RES update(@RequestBody SysDictDetail sysDictDetail) {
        return sysDictDetailService.update(sysDictDetail);
    }

    @SysLog(title = "字典管理", type = BusinessType.DELETE)
    @SaCheckPermission("system:dict:del")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable("ids") Integer[] ids) {
        return sysDictDetailService.delete(ids);
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/listDetail/{dictCode}")
    public RES listDetail(@PathVariable String dictCode) {
        return RES.ok(sysDictDetailService.listSysDictDetailByDictCode(dictCode));
    }
}
