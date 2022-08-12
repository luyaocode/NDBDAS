package com.asurplus.common.satoken;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.asurplus.system.service.SysRolePermissionService;
import com.asurplus.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class SaTokenStpInterfaceImpl implements StpInterface {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 1. 从session中获取
        List<String> list = (List<String>) StpUtil.getTokenSession().get(SaTokenSessionConst.ROLE_LIST);
        if (CollectionUtil.isNotEmpty(list)) {
            return list;
        }
        // 2. 数据库查询用户拥有的角色码
        list = sysUserRoleService.listRoleSignByUserId(String.valueOf(loginId));
        // 3. 存入session中
        if (CollectionUtil.isNotEmpty(list)) {
            StpUtil.getTokenSession().set(SaTokenSessionConst.ROLE_LIST, list);
        }
        // 4. 返回角色码集合
        return list;
    }

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 1. 声明权限码集合
        Set<String> permissionSet = new HashSet<>();
        // 2. 遍历角色列表，查询拥有的权限码
        List<String> list;
        for (String roleSign : getRoleList(loginId, loginType)) {
            // 如果是超级管理员
            if (StpUtil.hasRole("administrator")) {
                permissionSet.add("*");
                break;
            }
            // 根据角色码查询拥有的权限码
            list = sysRolePermissionService.listMenuSignByRoleSign(roleSign);
            if (CollectionUtil.isNotEmpty(list)) {
                permissionSet.addAll(list);
            }
        }
        // 3. 返回权限码集合
        return new ArrayList<>(permissionSet);
    }

}
