package com.asurplus.config.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.config.entity.SysApp;
import com.asurplus.config.mapper.SysAppMapper;
import com.asurplus.config.service.SysAppService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * app版本信息Service业务层处理
 *
 * @author lizhou
 * @date 2021-07-26
 */
@Service
public class SysAppServiceImpl extends ServiceImpl<SysAppMapper, SysApp> implements SysAppService {

    @Override
    public TableInfo list(SysApp sysApp, int isExport) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        if (1 == isExport) {
            pageVO.setPageSize(Integer.MAX_VALUE);
        }
        // 查询条件
        QueryWrapper<SysApp> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysApp.getAppName())) {
            queryWrapper.lambda().like(SysApp::getAppName, sysApp.getAppName());
        }
        if (StringUtils.isNotBlank(sysApp.getVersionName())) {
            queryWrapper.lambda().like(SysApp::getVersionName, sysApp.getVersionName());
        }
        if (StringUtils.isNotBlank(sysApp.getVersionCode())) {
            queryWrapper.lambda().eq(SysApp::getVersionCode, sysApp.getVersionCode());
        }
        if (null != sysApp.getIsForce()) {
            queryWrapper.lambda().eq(SysApp::getIsForce, sysApp.getIsForce());
        }
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(SysApp::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(SysApp::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(SysApp::getAppName).orderByDesc(SysApp::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES getSysApp(Long id) {
        return RES.ok(this.baseMapper.selectById(id));
    }

    @Override
    public RES add(SysApp sysApp) {
        if (null == sysApp) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysApp.getAppName())) {
            return RES.no("请输入APP名称");
        }
        if (StringUtils.isBlank(sysApp.getVersionName())) {
            return RES.no("请输入版本名称");
        }
        if (StringUtils.isBlank(sysApp.getVersionCode())) {
            return RES.no("请输入版本号");
        }
        LambdaQueryWrapper<SysApp> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysApp::getAppName, sysApp.getAppName());
        queryWrapper.eq(SysApp::getVersionName, sysApp.getVersionName());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("版本号已经存在");
        }
        sysApp.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(sysApp);
        return RES.ok();
    }

    @Override
    public RES update(SysApp sysApp) {
        if (null == sysApp || null == sysApp.getId() || 0 == sysApp.getId()) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysApp.getAppName())) {
            return RES.no("请输入APP名称");
        }
        if (StringUtils.isBlank(sysApp.getVersionName())) {
            return RES.no("请输入版本名称");
        }
        if (StringUtils.isBlank(sysApp.getVersionCode())) {
            return RES.no("请输入版本号");
        }
        LambdaQueryWrapper<SysApp> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysApp::getId, sysApp.getId());
        queryWrapper.eq(SysApp::getAppName, sysApp.getAppName());
        queryWrapper.eq(SysApp::getVersionName, sysApp.getVersionName());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("版本号已经存在");
        }
        sysApp.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysApp);
        return RES.ok();
    }

    @Override
    public RES delete(Long[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysApp> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysApp::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper);
        return RES.ok();
    }
}
