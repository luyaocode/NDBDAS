package com.asurplus.gateway.service.impl;

import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.mapper.GatewayMapper;
import com.asurplus.gateway.service.GatewayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GatewayServiceImpl extends ServiceImpl<GatewayMapper,GatewayInfo> implements GatewayService {
}
