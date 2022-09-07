package com.asurplus.system.vo;

import com.asurplus.system.entity.SysUserInfo;
import lombok.Data;

@Data
public class SysUserInfoAddVO extends SysUserInfo {

    // 角色ids
    private Integer roleIds[];
}
