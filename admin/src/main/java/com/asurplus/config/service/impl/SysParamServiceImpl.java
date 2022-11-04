package com.asurplus.config.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.asurplus.common.consts.SystemConst;
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.config.entity.SysParam;
import com.asurplus.config.mapper.SysParamMapper;
import com.asurplus.config.service.SysParamService;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 系统参数配置Service业务层处理
 *
 * @author lizhou
 * @date 2021-07-26
 */
@Service
public class SysParamServiceImpl extends ServiceImpl<SysParamMapper, SysParam> implements SysParamService {

    @Override
    public TableInfo list(SysParam sysParam) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<SysParam> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysParam.getName())) {
            queryWrapper.lambda().like(SysParam::getName, sysParam.getName());
        }
        if (StringUtils.isNotBlank(sysParam.getParamKey())) {
            queryWrapper.lambda().like(SysParam::getParamKey, sysParam.getParamKey());
        }
        if (null != sysParam.getType()) {
            queryWrapper.lambda().eq(SysParam::getType, sysParam.getType());
        }
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(SysParam::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(SysParam::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(SysParam::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES getSysParam(Integer id) {
        return RES.ok(this.baseMapper.selectById(id));
    }

    @Override
    public RES add(SysParam sysParam) {
        if (null == sysParam) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysParam.getParamKey())) {
            return RES.no("请输入参数键名");
        }
        if (StringUtils.isBlank(sysParam.getParamValue())) {
            return RES.no("请输入参数键值");
        }
        LambdaQueryWrapper<SysParam> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysParam::getParamKey, sysParam.getParamKey());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("参数键名已存在");
        }
        sysParam.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(sysParam);
        return RES.ok();
    }

    @Override
    @CacheEvict(value = SystemConst.SYS_PARAM, key = "#sysParam.paramKey")
    public RES update(SysParam sysParam) {
        if (null == sysParam || null == sysParam.getId() || 0 == sysParam.getId()) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysParam.getParamKey())) {
            return RES.no("请输入参数键名");
        }
        if (StringUtils.isBlank(sysParam.getParamValue())) {
            return RES.no("请输入参数键值");
        }
        LambdaQueryWrapper<SysParam> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysParam::getId, sysParam.getId());
        queryWrapper.eq(SysParam::getParamKey, sysParam.getParamKey());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("参数键名已存在");
        }
        sysParam.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysParam);
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysParam> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysParam::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper);
        return RES.ok();
    }

    @Override
    @Cacheable(value = SystemConst.SYS_PARAM, key = "#key")
    public String getParamValue(String key) {
        LambdaQueryWrapper<SysParam> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysParam::getParamValue);
        queryWrapper.eq(SysParam::getParamKey, key);
        SysParam one = this.getOne(queryWrapper, false);
        return null != one ? one.getParamValue() : null;
    }
}
