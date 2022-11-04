package com.asurplus.system.service.impl;

import com.asurplus.system.entity.SysUserRole;
import com.asurplus.system.mapper.SysUserRoleMapper;
import com.asurplus.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户-角色关系表 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-16
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public List<String> listRoleSignByUserId(String userId) {
        return this.baseMapper.listRoleSignByUserId(userId);
    }
}
