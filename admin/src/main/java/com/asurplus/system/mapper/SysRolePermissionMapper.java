package com.asurplus.system.mapper;

import com.asurplus.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色-权限关系表 Mapper 接口
 * </p>
 *
 * @author lizhou
 * @since 2021-07-16
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    /**
     * 查询该角色roleSign的所有权限标识
     *
     * @param roleSign
     * @return
     */
    List<String> listMenuSignByRoleSign(String roleSign);

    /**
     * 根据角色ID查询拥有的权限ids
     *
     * @param roleId
     * @return
     */
    List<Integer> listMenuIdsByRoleId(Integer roleId);
}
