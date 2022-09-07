package com.asurplus.monitor.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.quartz.QuartzManager;
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.monitor.entity.SysQuartzInfo;
import com.asurplus.monitor.mapper.SysQuartzInfoMapper;
import com.asurplus.monitor.service.SysQuartzInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 定时任务信息Service业务层处理
 *
 * @author lizhou
 * @date 2021-07-22
 */
@Service
public class SysQuartzInfoServiceImpl extends ServiceImpl<SysQuartzInfoMapper, SysQuartzInfo> implements SysQuartzInfoService {

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public TableInfo list(SysQuartzInfo sysQuartzInfo) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<SysQuartzInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysQuartzInfo.getClassName())) {
            queryWrapper.lambda().like(SysQuartzInfo::getClassName, sysQuartzInfo.getClassName());
        }
        if (null != sysQuartzInfo.getStatus()) {
            queryWrapper.lambda().eq(SysQuartzInfo::getStatus, sysQuartzInfo.getStatus());
        }
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(SysQuartzInfo::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(SysQuartzInfo::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(SysQuartzInfo::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES getSysQuartzInfo(Integer id) {
        return RES.ok(this.baseMapper.selectById(id));
    }

    @Override
    public RES add(SysQuartzInfo sysQuartzInfo) {
        if (null == sysQuartzInfo) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysQuartzInfo.getClassName())) {
            return RES.no("请输入任务类名");
        }
        if (StringUtils.isBlank(sysQuartzInfo.getCronExpression())) {
            return RES.no("请输入cron表达式");
        }
        if (!CronExpression.isValidExpression(sysQuartzInfo.getCronExpression())) {
            return RES.no("请输入正确的cron表达式");
        }
        // 查重
        LambdaQueryWrapper<SysQuartzInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysQuartzInfo::getClassName, sysQuartzInfo.getClassName());
        int rows = this.count(queryWrapper);
        if (0 < rows) {
            return RES.no("该任务类名已经存在，请检查后重试");
        }
        sysQuartzInfo.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(sysQuartzInfo);
        // 添加定时任务
        quartzManager.add(sysQuartzInfo.getClassName().trim(), sysQuartzInfo.getCronExpression().trim(), sysQuartzInfo.getParam(), sysQuartzInfo.getStatus());
        return RES.ok();
    }

    @Override
    public RES update(SysQuartzInfo sysQuartzInfo) {
        if (null == sysQuartzInfo || null == sysQuartzInfo.getId() || 0 == sysQuartzInfo.getId()) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysQuartzInfo.getClassName())) {
            return RES.no("请输入任务类名");
        }
        if (StringUtils.isBlank(sysQuartzInfo.getCronExpression())) {
            return RES.no("请输入cron表达式");
        }
        if (!CronExpression.isValidExpression(sysQuartzInfo.getCronExpression())) {
            return RES.no("请输入正确的cron表达式");
        }
        // 查重
        LambdaQueryWrapper<SysQuartzInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysQuartzInfo::getId, sysQuartzInfo.getId());
        queryWrapper.eq(SysQuartzInfo::getClassName, sysQuartzInfo.getClassName());
        SysQuartzInfo dbObj = this.getOne(queryWrapper, false);
        if (null != dbObj) {
            return RES.no("该任务类名已经存在，请检查后重试");
        }
        sysQuartzInfo.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysQuartzInfo);
        // 修改定时任务
        quartzManager.update(sysQuartzInfo.getClassName().trim(), sysQuartzInfo.getCronExpression().trim(), sysQuartzInfo.getParam(), sysQuartzInfo.getStatus());
        return RES.ok();
    }

    @Override
    public RES updateStatus(SysQuartzInfo sysQuartzInfo) {
        if (null == sysQuartzInfo || null == sysQuartzInfo.getId() || 0 == sysQuartzInfo.getId()) {
            return RES.no("请选择需要操作的数据");
        }
        SysQuartzInfo sysQuartz = this.baseMapper.selectById(sysQuartzInfo.getId());
        if (null == sysQuartz) {
            return RES.no("数据错误");
        }
        // 更改启动状态
        sysQuartz.setStatus(sysQuartzInfo.getStatus());
        sysQuartz.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysQuartz);
        // 启动
        if (0 == sysQuartzInfo.getStatus()) {
            quartzManager.start(sysQuartz.getClassName().trim());
        }
        // 停止
        else if (1 == sysQuartzInfo.getStatus()) {
            quartzManager.stop(sysQuartz.getClassName());
        }
        return RES.ok();
    }

    @Override
    public RES run(SysQuartzInfo sysQuartzInfo) {
        quartzManager.run(sysQuartzInfo.getClassName());
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysQuartzInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysQuartzInfo::getId, Arrays.asList(ids));
        List<SysQuartzInfo> list = this.baseMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            return RES.no("数据错误");
        }
        // 删除
        this.baseMapper.delete(queryWrapper);
        // 删除定时任务
        for (SysQuartzInfo item : list) {
            quartzManager.delete(item.getClassName());
        }
        return RES.ok();
    }
}
