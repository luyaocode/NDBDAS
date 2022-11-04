package com.asurplus.system.vo;

import com.asurplus.system.entity.SysRoleInfo;
import lombok.Data;

@Data
public class SysRoleInfoAuthVO extends SysRoleInfo {

    /**
     * 是否选中
     */
    private boolean flag = false;
}
