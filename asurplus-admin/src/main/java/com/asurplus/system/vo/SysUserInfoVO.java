package com.asurplus.system.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.asurplus.system.entity.SysUserInfo;
import lombok.Data;

@Data
public class SysUserInfoVO extends SysUserInfo {

    @Excel(name = "所属部门", width = 30)
    private String deptName;

    private Integer[] roleIds;

    private Integer roleId;

    @Excel(name = "所属角色", width = 30)
    private String roleName;
}
