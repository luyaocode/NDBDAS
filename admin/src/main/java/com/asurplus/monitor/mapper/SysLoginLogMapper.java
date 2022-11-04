package com.asurplus.monitor.mapper;

import com.asurplus.monitor.entity.SysLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 登录日志 Mapper 接口
 * </p>
 *
 * @author lizhou
 * @since 2021-07-20
 */
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

    void deleteAll();
}
