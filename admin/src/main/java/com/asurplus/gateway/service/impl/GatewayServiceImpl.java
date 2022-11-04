package com.asurplus.gateway.service.impl;

<<<<<<< HEAD
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
=======

import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;

import cn.dev33.satoken.stp.StpUtil;
import com.asurplus.common.consts.SystemConst;
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.config.entity.SysParam;

>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.mapper.GatewayMapper;
import com.asurplus.gateway.service.GatewayService;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
<<<<<<< HEAD
=======


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
<<<<<<< HEAD
import org.springframework.stereotype.Service;

=======

import org.springframework.stereotype.Service;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Arrays;


>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
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
<<<<<<< HEAD
=======
    @Override
    public RES getById(Integer id) {
        //        入参检验
        if (null == id) {
            return RES.no("输入数据错误！");
        }
        return RES.ok(this.baseMapper.selectById(id));
    }

    @Override
    public RES add(GatewayInfo gatewayInfo) {
        if (null == gatewayInfo) {
            return RES.no("提交错误");
        }
        if (null == gatewayInfo.getName()) {
            return RES.no("请输入网关名称");
        }
        if (null == gatewayInfo.getIp()) {
            return RES.no("请输入IP地址");
        }
        if (null == gatewayInfo.getPort()) {
            return RES.no("请输入端口号");
        }

        //查重
        LambdaQueryWrapper<GatewayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GatewayInfo::getPort, gatewayInfo.getPort());
        int count = this.baseMapper.selectCount(queryWrapper);
        if (0 < count) {
            return RES.no("端口已存在");
        }
        gatewayInfo.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(gatewayInfo);
        return RES.ok();
    }
    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }

        LambdaQueryWrapper<GatewayInfo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(GatewayInfo::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper2);
        return RES.ok();
    }

    @Override
    public RES update(GatewayInfo gatewayInfo) {
        if (null == gatewayInfo || null == gatewayInfo.getId() || 0 == gatewayInfo.getId()) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(gatewayInfo.getIp())) {
            return RES.no("请输入网关IP地址");
        }
        if (null == gatewayInfo.getPort()) {
            return RES.no("请输入端口号");
        }
        LambdaQueryWrapper<GatewayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(GatewayInfo::getId, gatewayInfo.getId());
        queryWrapper.eq(GatewayInfo::getPort, gatewayInfo.getPort());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("端口号已存在");
        }
        gatewayInfo.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(gatewayInfo);
        return RES.ok();
    }

>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5


}
