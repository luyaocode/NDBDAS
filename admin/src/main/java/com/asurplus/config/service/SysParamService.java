package com.asurplus.config.service;

import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.asurplus.config.entity.SysParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统参数配置Service接口
 *
 * @author lizhou
 * @date 2021-07-26
 */
public interface SysParamService extends IService<SysParam> {

    /**
     * 分页查询
     */
    TableInfo list(SysParam sysParam);

    /**
     * 获取SysParam对象
     */
    RES getSysParam(Integer id);

    /**
     * 新增
     */
    RES add(SysParam sysParam);

    /**
     * 修改
     */
    RES update(SysParam sysParam);

    /**
     * 删除
     */
    RES delete(Integer[] ids);

    /**
     * 根据参数键，获取参数值
     */
    String getParamValue(String key);
}
