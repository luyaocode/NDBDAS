package com.asurplus.monitor.service.impl;

import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.ip.AddressUtils;
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.entity.SysOperLog;
import com.asurplus.monitor.mapper.SysOperLogMapper;
import com.asurplus.monitor.service.SysOperLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作日志记录Service业务层处理
 *
 * @author lizhou
 * @date 2021-07-26
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

    @Autowired
    private AddressUtils addressUtils;

    @Override
    public TableInfo list(SysOperLog sysOperLog) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<SysOperLog> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysOperLog.getTitle())) {
            queryWrapper.lambda().like(SysOperLog::getTitle, sysOperLog.getTitle());
        }
        if (StringUtils.isNotBlank(sysOperLog.getOperName())) {
            queryWrapper.lambda().like(SysOperLog::getOperName, sysOperLog.getOperName());
        }
        if (null != sysOperLog.getType()) {
            queryWrapper.lambda().eq(SysOperLog::getType, sysOperLog.getType());
        }
        if (null != sysOperLog.getStatus()) {
            queryWrapper.lambda().eq(SysOperLog::getStatus, sysOperLog.getStatus());
        }
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(SysOperLog::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(SysOperLog::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(SysOperLog::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Async
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        if (null == sysOperLog) {
            return;
        }
        if (StringUtils.isNotBlank(sysOperLog.getIp())) {
            sysOperLog.setLocation(addressUtils.getAddress(sysOperLog.getIp()));
        }
        this.baseMapper.insert(sysOperLog);
    }

    @Override
    public RES delete(Long[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysOperLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysOperLog::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper);
        return RES.ok();
    }

    @Override
    public RES deleteAll() {
        this.baseMapper.deleteAll();
        return RES.ok();
    }
}
