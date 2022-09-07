package com.asurplus.system.service;

import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysRoleInfo;
import com.asurplus.system.entity.SysUserRole;
import com.asurplus.system.vo.SysRoleInfoAddVO;
import com.asurplus.system.vo.SysUserInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-15
 */
public interface SysRoleInfoService extends IService<SysRoleInfo> {

    TableInfo list(SysRoleInfo sysRoleInfo);

    RES getSysRoleInfo(Integer id);

    RES add(SysRoleInfoAddVO sysRoleInfo);

    RES update(SysRoleInfoAddVO sysRoleInfo);

    RES updateStatus(SysRoleInfo sysRoleInfo);

    RES delete(Integer[] ids);

    TableInfo allocatedList(SysUserInfoVO sysUserInfo);

    TableInfo unAllocatedList(SysUserInfoVO sysUserInfo);

    RES addAuthAll(Integer roleId, Integer[] ids);

    RES updateCancelAuth(SysUserRole sysUserRole);

    RES updateCancelAuthAll(Long roleId, Long[] userIds);
}
