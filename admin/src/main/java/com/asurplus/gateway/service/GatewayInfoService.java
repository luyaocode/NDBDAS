package com.asurplus.gateway.service;

import com.asurplus.common.utils.RES;
import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Luyao
 * @since 2022-9-15
 */

public interface GatewayInfoService extends IService<GatewayInfo> {
    /**
     * 分页查询
     */
    TableInfo list(GatewayInfo gatewayInfo);
    /**
     * 根据id查询
     */
    RES getById(Integer id);

    /**
     * 连接网关
     * @param id
     * @return
     */
    RES connect(Integer id);

    RES updateStatus(GatewayInfo gatewayInfo);
    /**
     * 新增
     */
    RES add(GatewayInfo gatewayInfo);
    /**
     * 删除
     */
    RES delete(Integer[] ids);

    /**
     * 修改
     */
    RES update(GatewayInfo gatewayInfo);
}

