package com.asurplus.monitor.service.impl;

import com.asurplus.common.ip.AddressUtils;
import com.asurplus.common.ip.IpUtils;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.*;
import com.asurplus.monitor.entity.SysLoginLog;
import com.asurplus.monitor.mapper.SysLoginLogMapper;
import com.asurplus.monitor.service.SysLoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-20
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    @Autowired
    private AddressUtils addressUtils;

    @Async
    @Override
    public void saveSysLoginLog(String account, Integer status, String remark, HttpServletRequest request) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setAccount(account);
        sysLoginLog.setStatus(status);
        sysLoginLog.setRemark(remark);
        // IP地址
        sysLoginLog.setIp(IpUtils.getIpAddr(request));
        // 地理位置
        sysLoginLog.setLocation(addressUtils.getAddress(sysLoginLog.getIp()));
        // UserAgent信息
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 浏览器
        sysLoginLog.setBrowser(userAgent.getBrowser().getName());
        // 操作系统
        sysLoginLog.setOs(userAgent.getOperatingSystem().getName());
        // 设备
        String deviceName = userAgent.getOperatingSystem().getDeviceType().getName();
        if (StringUtils.isBlank(deviceName)) {
            deviceName = "Default";
        } else if ("Computer".equals(deviceName)) {
            deviceName = "PC";
        } else if ("Mobile".equals(deviceName)) {
            deviceName = "Phone";
        }
        sysLoginLog.setDevice(deviceName);
        this.baseMapper.insert(sysLoginLog);
    }

    @Override
    public TableInfo list(SysLoginLog sysLoginLog, boolean isExport) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        if (isExport) {
            pageVO.setPageSize(Integer.MAX_VALUE);
        }
        // 查询条件
        QueryWrapper<SysLoginLog> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysLoginLog.getIp())) {
            queryWrapper.lambda().like(SysLoginLog::getId, sysLoginLog.getId());
        }
        if (StringUtils.isNotBlank(sysLoginLog.getAccount())) {
            queryWrapper.lambda().like(SysLoginLog::getAccount, sysLoginLog.getAccount());
        }
        if (null != sysLoginLog.getStatus()) {
            queryWrapper.lambda().eq(SysLoginLog::getStatus, sysLoginLog.getStatus());
        }
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(SysLoginLog::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(SysLoginLog::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(SysLoginLog::getCreateTime).orderByDesc(SysLoginLog::getId);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysLoginLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysLoginLog::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper);
        return RES.ok();
    }

    @Override
    public RES deleteAll() {
        this.baseMapper.deleteAll();
        return RES.ok();
    }
}
