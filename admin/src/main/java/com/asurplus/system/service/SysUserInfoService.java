package com.asurplus.system.service;

import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysUserInfo;
import com.asurplus.system.vo.SysUserInfoAddVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-15
 */
public interface SysUserInfoService extends IService<SysUserInfo> {

    TableInfo list(SysUserInfo sysUserInfo, boolean isExport);

    RES getSysUserInfo(Integer id);

    RES add(SysUserInfoAddVO sysUserInfoAddVO);

    RES update(SysUserInfoAddVO sysUserInfoAddVO);

    RES delete(Integer[] ids);

    RES updateStatus(SysUserInfo sysUserInfo);

    RES updateRestPwd(SysUserInfo sysUserInfo);

    RES listAuthRole(Integer userId);

    RES updateAuthRole(Integer userId, Integer[] roleIds);
}
