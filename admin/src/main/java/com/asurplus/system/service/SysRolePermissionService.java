package com.asurplus.system.service;

import com.asurplus.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色-权限关系表 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-16
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 查询该角色roleSign的所有权限标识
     *
     * @param roleSign
     * @return
     */
    List<String> listMenuSignByRoleSign(String roleSign);
}
