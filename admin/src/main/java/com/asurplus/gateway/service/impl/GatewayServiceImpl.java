package com.asurplus.gateway.service.impl;

import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.mapper.GatewayMapper;
import com.asurplus.gateway.service.GatewayService;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class GatewayServiceImpl extends ServiceImpl<GatewayMapper, GatewayInfo> implements GatewayService {
    @Override
    public TableInfo list(GatewayInfo gatewayInfo) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<GatewayInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(gatewayInfo.getName())) {
            queryWrapper.lambda().like(GatewayInfo::getName, gatewayInfo.getName());
        }
        if (StringUtils.isNotBlank(gatewayInfo.getIp())) {
            queryWrapper.lambda().like(GatewayInfo::getIp, gatewayInfo.getIp());
        }
        if (null != gatewayInfo.getPort()){
            queryWrapper.lambda().like(GatewayInfo::getPort, gatewayInfo.getPort());
        }
        if (null != gatewayInfo.getType()) {
            queryWrapper.lambda().eq(GatewayInfo::getType, gatewayInfo.getType());
        }
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(GatewayInfo::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(GatewayInfo::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(GatewayInfo::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }


}
