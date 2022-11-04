package com.asurplus.system.service;

import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysPermissionInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
public interface SysPermissionInfoService extends IService<SysPermissionInfo> {

    List<SysPermissionInfo> list(SysPermissionInfo sysPermissionInfo);

    RES getSysPermissionInfo(Integer id);

    RES add(SysPermissionInfo sysPermissionInfo);

    RES update(SysPermissionInfo sysPermissionInfo);

    RES delete(Integer id);

    RES treeSelect(SysPermissionInfo sysPermissionInfo);

    RES rolePermissionTreeSelect(Integer roleId);
}
