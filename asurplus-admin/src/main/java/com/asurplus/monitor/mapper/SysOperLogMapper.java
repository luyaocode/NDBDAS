package com.asurplus.monitor.mapper;

import com.asurplus.monitor.entity.SysOperLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 操作日志记录Mapper接口
 * 
 * @author lizhou
 * @date 2021-07-26
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

    void deleteAll();
}
