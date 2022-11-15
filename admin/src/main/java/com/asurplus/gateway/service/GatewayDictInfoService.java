package com.asurplus.gateway.service;

import com.asurplus.gateway.entity.GatewayDictInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Luyao
 * @since 2022-9-15
 */

public interface GatewayDictInfoService extends IService<GatewayDictInfo> {
    List<GatewayDictInfo> list(String dictType);
}

