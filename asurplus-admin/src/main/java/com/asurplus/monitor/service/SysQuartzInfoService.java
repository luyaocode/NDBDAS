package com.asurplus.monitor.service;

import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.entity.SysQuartzInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 定时任务信息Service接口
 *
 * @author lizhou
 * @date 2021-07-22
 */
public interface SysQuartzInfoService extends IService<SysQuartzInfo> {

    /**
     * 分页查询SysQuartzInfo对象
     */
    TableInfo list(SysQuartzInfo sysQuartzInfo);

    /**
     * 获取SysQuartzInfo对象
     */
    RES getSysQuartzInfo(Integer id);

    /**
     * 新增SysQuartzInfo对象
     */
    RES add(SysQuartzInfo sysQuartzInfo);

    /**
     * 修改SysQuartzInfo对象
     */
    RES update(SysQuartzInfo sysQuartzInfo);

    /**
     * 修改定时任务启停状态
     *
     * @return
     */
    RES updateStatus(SysQuartzInfo sysQuartzInfo);

    /**
     * 立即执行定时任务
     */
    RES run(SysQuartzInfo sysQuartzInfo);

    /**
     * 删除SysQuartzInfo对象
     */
    RES delete(Integer[] ids);
}
