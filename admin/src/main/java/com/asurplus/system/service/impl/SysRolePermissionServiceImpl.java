package com.asurplus.system.service.impl;

import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.asurplus.common.satoken.SaTokenSessionConst;
import com.asurplus.system.entity.SysRolePermission;
import com.asurplus.system.mapper.SysRolePermissionMapper;
import com.asurplus.system.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色-权限关系表 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-16
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public List<String> listMenuSignByRoleSign(String roleSign) {
        // 从session中获取
        List<String> list = (List<String>) SaSessionCustomUtil.getSessionById("sys-role-" + roleSign).get(SaTokenSessionConst.PERMISSION_LIST);
        if (CollectionUtil.isNotEmpty(list)) {
            return list;
        }
        list = this.baseMapper.listMenuSignByRoleSign(roleSign);
        // 存入session中
        if (CollectionUtil.isNotEmpty(list)) {
            SaSessionCustomUtil.getSessionById("sys-role-" + roleSign).set(SaTokenSessionConst.PERMISSION_LIST, list);
        }
        return list;
    }
}
