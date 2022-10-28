package com.asurplus.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.PasswordUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysRoleInfo;
import com.asurplus.system.entity.SysUserInfo;
import com.asurplus.system.entity.SysUserRole;
import com.asurplus.system.mapper.SysRoleInfoMapper;
import com.asurplus.system.mapper.SysUserInfoMapper;
import com.asurplus.system.mapper.SysUserRoleMapper;
import com.asurplus.system.service.SysUserInfoService;
import com.asurplus.system.service.SysUserRoleService;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.SysUserInfoAddVO;
import com.asurplus.system.vo.SysUserInfoVO;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-15
 */
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements SysUserInfoService {

    @Autowired
    private SysRoleInfoMapper sysRoleInfoMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public TableInfo list(SysUserInfo sysUserInfo, boolean isExport) {
        // 获取分页对象
//        PageUtils做了什么：通过ServletUtils获取前端传入的参数，赋值给pageVO。关键属性：beginTime和endTime
        PageVO pageVO = PageUtils.getPageVO();
        System.out.println("pageVo是什么："+pageVO);
//        例如：pageVo是什么：PageVO{pageNum=1, pageSize=10, field='null', isAsc=true, beginTime='2022-10-03', endTime='2022-10-12'}
        if (isExport) {
            pageVO.setPageSize(Integer.MAX_VALUE);
        }
        // 查询条件
        QueryWrapper<SysUserInfoVO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysUserInfo.getName())) {
            queryWrapper.like("a.name", sysUserInfo.getName());
        }
        if (StringUtils.isNotBlank(sysUserInfo.getAccount())) {
            queryWrapper.like("a.account", sysUserInfo.getAccount());
        }
        if (StringUtils.isNotBlank(sysUserInfo.getPhone())) {
            queryWrapper.like("a.phone", sysUserInfo.getPhone());
        }
        if (null != sysUserInfo.getStatus()) {
            queryWrapper.eq("a.status", sysUserInfo.getStatus());
        }
        if (null != sysUserInfo.getDeptId()) {
            queryWrapper.and(i -> i.eq("b.id", sysUserInfo.getDeptId()).or().eq("b.pid", sysUserInfo.getDeptId()));
        }
        if (null != sysUserInfo.getSex()) {
            queryWrapper.eq("a.sex", sysUserInfo.getSex());
        }
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.ge("a.create_time", DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.le("a.create_time", DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc("a." + pageVO.getField());
            } else {
                queryWrapper.orderByDesc("a." + pageVO.getField());
            }
        } else {
            queryWrapper.orderByDesc("a.create_time");
        }
        return TableInfo.ok(this.baseMapper.list(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES getSysUserInfo(Integer id) {
        RES res = RES.ok();
        LambdaQueryWrapper<SysRoleInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysRoleInfo::getId, SysRoleInfo::getName);
        queryWrapper.orderByDesc(SysRoleInfo::getSort);
        List<SysRoleInfo> roleInfos = sysRoleInfoMapper.selectList(queryWrapper);
        res.put("roles", roleInfos);
        if (null != id && 0 != id) {
            // 用户数据
            res.put("data", this.baseMapper.selectById(id));
            // 用户的角色id
            res.put("roleIds", sysUserRoleMapper.listRoleIdsByUserId(id));
        }
        return res;
    }

    @Override
    public RES add(SysUserInfoAddVO sysUserInfoAddVO) {
        if (null == sysUserInfoAddVO) {
            return RES.no("数据错误 ");
        }
        if (StringUtils.isBlank(sysUserInfoAddVO.getAccount())) {
            return RES.no("请输入账户");
        }
        if (StringUtils.isBlank(sysUserInfoAddVO.getPassword())) {
            return RES.no("请输入密码");
        }
        // 查重
        LambdaQueryWrapper<SysUserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserInfo::getAccount, sysUserInfoAddVO.getAccount());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("该账户已经存在");
        }
        // 加密密码
        sysUserInfoAddVO.setPassword(PasswordUtils.getPassword(sysUserInfoAddVO.getAccount(), sysUserInfoAddVO.getPassword()));
        sysUserInfoAddVO.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(sysUserInfoAddVO);
        // 保存角色
        if (null != sysUserInfoAddVO.getRoleIds() && 0 < sysUserInfoAddVO.getRoleIds().length) {
            List<SysUserRole> list = new ArrayList<>();
            SysUserRole sysUserRole = null;
            for (Integer item : sysUserInfoAddVO.getRoleIds()) {
                sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUserInfoAddVO.getId());
                sysUserRole.setRoleId(item);
                list.add(sysUserRole);
            }
            sysUserRoleService.saveBatch(list);
        }
        return RES.ok();
    }

    @Override
    public RES update(SysUserInfoAddVO sysUserInfoAddVO) {
        if (null == sysUserInfoAddVO || null == sysUserInfoAddVO.getId() || 0 == sysUserInfoAddVO.getId()) {
            return RES.no("数据错误 ");
        }
        if (StringUtils.isBlank(sysUserInfoAddVO.getAccount())) {
            return RES.no("请输入账户");
        }
        if (StringUtils.isBlank(sysUserInfoAddVO.getPassword())) {
            return RES.no("请输入密码");
        }
        // 查重
        LambdaQueryWrapper<SysUserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysUserInfo::getId, sysUserInfoAddVO.getId());
        queryWrapper.eq(SysUserInfo::getAccount, sysUserInfoAddVO.getAccount());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("该账户已经存在");
        }
        sysUserInfoAddVO.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysUserInfoAddVO);
        // 先删除角色
        LambdaQueryWrapper<SysUserRole> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(SysUserRole::getUserId, sysUserInfoAddVO.getId());
        sysUserRoleService.remove(queryWrapper1);
        // 保存角色
        if (null != sysUserInfoAddVO.getRoleIds() && 0 < sysUserInfoAddVO.getRoleIds().length) {
            List<SysUserRole> list = new ArrayList<>();
            SysUserRole sysUserRole = null;
            for (Integer item : sysUserInfoAddVO.getRoleIds()) {
                sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUserInfoAddVO.getId());
                sysUserRole.setRoleId(item);
                list.add(sysUserRole);
            }
            sysUserRoleService.saveBatch(list);
        }
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysUserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysUserInfo::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper);
        // 先删除角色
        LambdaQueryWrapper<SysUserRole> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(SysUserRole::getUserId, Arrays.asList(ids));
        sysUserRoleService.remove(queryWrapper1);
        return RES.ok();
    }

    @Override
    public RES updateStatus(SysUserInfo sysUserInfo) {
        if (null == sysUserInfo || null == sysUserInfo.getId() || 0 == sysUserInfo.getId()) {
            return RES.no("请选择需要操作的数据");
        }
        if (null == sysUserInfo.getStatus()) {
            return RES.no("数据错误");
        }
        SysUserInfo dbObj = this.baseMapper.selectById(sysUserInfo.getId());
        dbObj.setStatus(sysUserInfo.getStatus());
        this.baseMapper.updateById(dbObj);
        return RES.ok();
    }

    @Override
    public RES updateRestPwd(SysUserInfo sysUserInfo) {
        if (null == sysUserInfo) {
            return RES.no("数据错误 ");
        }
        if (null == sysUserInfo.getId() || 0 == sysUserInfo.getId()) {
            return RES.no("请选择需要操作的数据");
        }
        SysUserInfo dbObj = this.baseMapper.selectById(sysUserInfo.getId());
        dbObj.setPassword(PasswordUtils.getPassword(dbObj.getAccount(), sysUserInfo.getPassword()));
        dbObj.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(dbObj);
        return RES.ok();
    }

    @Override
    public RES listAuthRole(Integer userId) {
        RES res = RES.ok();
        res.put("user", this.baseMapper.selectById(userId));
        res.put("roles", sysUserRoleMapper.listRoleInfo(userId));
        return res;
    }

    @Override
    public RES updateAuthRole(Integer userId, Integer[] roleIds) {
        if (null == userId || 0 == userId) {
            return RES.no("请选择需要操作的数据");
        }
        // 先删除
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        sysUserRoleMapper.delete(queryWrapper);
        // 再保存
        if (null != roleIds && 0 < roleIds.length) {
            List<SysUserRole> list = new ArrayList<>();
            SysUserRole sysUserRole;
            for (Integer item : roleIds) {
                sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(item);
                list.add(sysUserRole);
            }
            sysUserRoleService.saveBatch(list);
        }
        return RES.ok();
    }
}
