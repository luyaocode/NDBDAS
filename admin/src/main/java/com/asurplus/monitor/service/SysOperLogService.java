package com.asurplus.monitor.service;

import com.asurplus.monitor.entity.SysOperLog;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 操作日志记录Service接口
 *
 * @author lizhou
 * @date 2021-07-26
 */
public interface SysOperLogService extends IService<SysOperLog> {

    /**
     * 分页查询
     */
    TableInfo list(SysOperLog sysOperLog);

    /**
     * 新增
     */
    void saveSysOperLog(SysOperLog sysOperLog);

    /**
     * 删除
     */
    RES delete(Long[] ids);

    /**
     * 清空
     */
    RES deleteAll();
}
