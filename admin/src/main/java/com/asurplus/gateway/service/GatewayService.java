package com.asurplus.gateway.service;

import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Luyao
 * @since 2022-9-15
 */

public interface GatewayService extends IService<GatewayInfo> {
    /**
     * 分页查询
     */
    TableInfo list(GatewayInfo gatewayInfo);
}
