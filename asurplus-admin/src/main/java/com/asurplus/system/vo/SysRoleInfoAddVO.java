package com.asurplus.system.vo;

import com.asurplus.system.entity.SysRoleInfo;
import lombok.Data;

@Data
public class SysRoleInfoAddVO extends SysRoleInfo {

    // 权限ids
    private Integer[] menuIds;
}
