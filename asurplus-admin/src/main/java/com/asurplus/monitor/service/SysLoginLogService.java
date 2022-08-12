package com.asurplus.monitor.service;

import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.entity.SysLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-20
 */
public interface SysLoginLogService extends IService<SysLoginLog> {

    void saveSysLoginLog(String account, Integer status, String remark, HttpServletRequest request);

    TableInfo list(SysLoginLog sysLoginLog, boolean isExport);

    RES delete(Integer[] ids);

    RES deleteAll();
}
