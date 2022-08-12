package com.asurplus.config.service;

import com.asurplus.config.entity.SysApp;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * app版本信息Service接口
 * 
 * @author lizhou
 * @date 2021-07-26
 */
public interface SysAppService extends IService<SysApp> {

    /**
     * 分页查询
     */
    TableInfo list(SysApp sysApp, int isExport);

    /**
     * 获取SysApp对象
     */
    RES getSysApp(Long id);

    /**
     * 新增
     */
    RES add(SysApp sysApp);

    /**
     * 修改
     */
    RES update(SysApp sysApp);

    /**
     * 删除
     */
    RES delete(Long[] ids);
}
