package com.asurplus.system.service;

import com.asurplus.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户-角色关系表 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-16
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 查询该用户的所有角色码
     * @param userId
     * @return
     */
    List<String> listRoleSignByUserId(String userId);
}
