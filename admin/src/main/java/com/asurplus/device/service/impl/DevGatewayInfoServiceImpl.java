package com.asurplus.device.service.impl;

import com.asurplus.device.entity.DevGatewayInfo;
import com.asurplus.device.mapper.DevGatewayInfoMapper;
import com.asurplus.device.service.DevGatewayInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luyao
 */
@Service
public class DevGatewayInfoServiceImpl extends ServiceImpl<DevGatewayInfoMapper, DevGatewayInfo> implements DevGatewayInfoService {
    @Autowired
    private DevGatewayInfoMapper devGatewayInfoMapper;
    @Override
    public boolean save(DevGatewayInfo devGatewayInfo) {
        LambdaQueryWrapper<DevGatewayInfo> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DevGatewayInfo::getDevId,devGatewayInfo.getDevId());
        if(devGatewayInfoMapper.selectCount(lambdaQueryWrapper)<1){
            devGatewayInfoMapper.insert(devGatewayInfo);
        }else{
            devGatewayInfoMapper.update(devGatewayInfo,lambdaQueryWrapper);
        }
        return true;
    }
}
