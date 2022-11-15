package com.asurplus.gateway.service.impl;

import com.asurplus.gateway.entity.GatewayDictInfo;
import com.asurplus.gateway.mapper.GatewayDictInfoMapper;
import com.asurplus.gateway.service.GatewayDictInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenluyao
 */
@Service
public class GatewayDictInfoServiceImpl extends ServiceImpl<GatewayDictInfoMapper, GatewayDictInfo> implements GatewayDictInfoService {

    @Override
    public List<GatewayDictInfo> list(String dictType) {
        return this.baseMapper.selectList(new QueryWrapper<GatewayDictInfo>().eq("item",dictType));
    }
}
