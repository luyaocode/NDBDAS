package com.asurplus.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysRoleInfo;
import com.asurplus.system.entity.SysRolePermission;
import com.asurplus.system.entity.SysUserRole;
import com.asurplus.system.mapper.SysRoleInfoMapper;
import com.asurplus.system.mapper.SysUserRoleMapper;
import com.asurplus.system.service.SysRoleInfoService;
import com.asurplus.system.service.SysRolePermissionService;
import com.asurplus.system.service.SysUserRoleService;
import com.asurplus.system.vo.SysRoleInfoAddVO;
import com.asurplus.system.vo.SysUserInfoVO;
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
 * 角色信息表 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-15
 */
@Service
public class SysRoleInfoServiceImpl extends ServiceImpl<SysRoleInfoMapper, SysRoleInfo> implements SysRoleInfoService {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public TableInfo list(SysRoleInfo sysRoleInfo) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<SysRoleInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysRoleInfo.getName())) {
            queryWrapper.lambda().like(SysRoleInfo::getName, sysRoleInfo.getName());
        }
        if (StringUtils.isNotBlank(sysRoleInfo.getSign())) {
            queryWrapper.lambda().like(SysRoleInfo::getSign, sysRoleInfo.getSign());
        }
        if (null != sysRoleInfo.getStatus()) {
            queryWrapper.lambda().eq(SysRoleInfo::getStatus, sysRoleInfo.getStatus());
        }
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(SysRoleInfo::getSort).orderByDesc(SysRoleInfo::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES getSysRoleInfo(Integer id) {
        return RES.ok(this.baseMapper.selectById(id));
    }

    @Override
    public RES add(SysRoleInfoAddVO sysRoleInfo) {
        if (null == sysRoleInfo) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysRoleInfo.getName())) {
            return RES.no("请输入角色名称");
        }
        if (StringUtils.isBlank(sysRoleInfo.getSign())) {
            return RES.no("请输入角色标识");
        }
        // 查重
        LambdaQueryWrapper<SysRoleInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.and(i -> i.eq(SysRoleInfo::getName, sysRoleInfo.getName()).or().eq(SysRoleInfo::getSign, sysRoleInfo.getSign()));
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("该角色名称或标识已经存在");
        }
        sysRoleInfo.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(sysRoleInfo);
        // 分配权限
        if (null != sysRoleInfo.getMenuIds() && 0 < sysRoleInfo.getMenuIds().length) {
            List<SysRolePermission> list = new ArrayList<>();
            SysRolePermission sysRolePermission = null;
            for (Integer item : sysRoleInfo.getMenuIds()) {
                sysRolePermission = new SysRolePermission();
                sysRolePermission.setRoleId(sysRoleInfo.getId());
                sysRolePermission.setPermissionId(item);
                list.add(sysRolePermission);
            }
            sysRolePermissionService.saveBatch(list);
        }
        return RES.ok();
    }

    @Override
    public RES update(SysRoleInfoAddVO sysRoleInfo) {
        if (null == sysRoleInfo || null == sysRoleInfo.getId() || 0 == sysRoleInfo.getId()) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysRoleInfo.getName())) {
            return RES.no("请输入角色名称");
        }
        if (StringUtils.isBlank(sysRoleInfo.getSign())) {
            return RES.no("请输入角色标识");
        }
        // 查重
        LambdaQueryWrapper<SysRoleInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysRoleInfo::getId, sysRoleInfo.getId());
        queryWrapper.and(i -> i.eq(SysRoleInfo::getName, sysRoleInfo.getName()).or().eq(SysRoleInfo::getSign, sysRoleInfo.getSign()));
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("该角色名称或标识已经存在");
        }
        sysRoleInfo.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysRoleInfo);
        // 先删除权限
        LambdaQueryWrapper<SysRolePermission> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(SysRolePermission::getRoleId, sysRoleInfo.getId());
        sysRolePermissionService.remove(queryWrapper1);
        // 分配权限
        if (null != sysRoleInfo.getMenuIds() && 0 < sysRoleInfo.getMenuIds().length) {
            List<SysRolePermission> list = new ArrayList<>();
            SysRolePermission sysRolePermission = null;
            for (Integer item : sysRoleInfo.getMenuIds()) {
                sysRolePermission = new SysRolePermission();
                sysRolePermission.setRoleId(sysRoleInfo.getId());
                sysRolePermission.setPermissionId(item);
                list.add(sysRolePermission);
            }
            sysRolePermissionService.saveBatch(list);
        }
        return RES.ok();
    }

    @Override
    public RES updateStatus(SysRoleInfo sysRoleInfo) {
        if (null == sysRoleInfo || null == sysRoleInfo.getId() || 0 == sysRoleInfo.getId()) {
            return RES.no("数据错误");
        }
        if (null == sysRoleInfo.getStatus()) {
            return RES.no("数据错误");
        }
        SysRoleInfo dbObj = this.baseMapper.selectById(sysRoleInfo.getId());
        dbObj.setStatus(sysRoleInfo.getStatus());
        this.baseMapper.updateById(dbObj);
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysUserRole::getRoleId, Arrays.asList(ids));
        int count = sysUserRoleService.count(queryWrapper);
        if (0 < count) {
            return RES.no("角色已分配，不允许删除");
        }
        LambdaQueryWrapper<SysRoleInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(SysRoleInfo::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper1);
        // 先删除权限
        LambdaQueryWrapper<SysRolePermission> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(SysRolePermission::getRoleId, Arrays.asList(ids));
        sysRolePermissionService.remove(queryWrapper2);
        return RES.ok();
    }

    @Override
    public TableInfo allocatedList(SysUserInfoVO sysUserInfo) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<SysUserInfoVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.del_flag", 0);
        if (null != sysUserInfo.getRoleId() && 0 != sysUserInfo.getRoleId()) {
            queryWrapper.exists("( SELECT sur.user_id FROM sys_user_role sur WHERE sur.role_id = " + sysUserInfo.getRoleId() + " AND a.id = sur.user_id )");
        }
        if (StringUtils.isNotBlank(sysUserInfo.getName())) {
            queryWrapper.like("a.name", sysUserInfo.getName());
        }
        if (StringUtils.isNotBlank(sysUserInfo.getPhone())) {
            queryWrapper.like("a.phone", sysUserInfo.getPhone());
        }
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc("a." + pageVO.getField());
            } else {
                queryWrapper.orderByDesc("a." + pageVO.getField());
            }
        } else {
            queryWrapper.orderByDesc("a.create_time");
        }
        return TableInfo.ok(this.baseMapper.listSysUserInfoVO(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public TableInfo unAllocatedList(SysUserInfoVO sysUserInfo) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<SysUserInfoVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.del_flag", 0);
        if (null != sysUserInfo.getRoleId() && 0 != sysUserInfo.getRoleId()) {
            queryWrapper.notExists("( SELECT sur.user_id FROM sys_user_role sur WHERE sur.role_id = " + sysUserInfo.getRoleId() + " AND a.id = sur.user_id )");
        }
        if (StringUtils.isNotBlank(sysUserInfo.getName())) {
            queryWrapper.like("a.name", sysUserInfo.getName());
        }
        if (StringUtils.isNotBlank(sysUserInfo.getPhone())) {
            queryWrapper.like("a.phone", sysUserInfo.getPhone());
        }
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc("a." + pageVO.getField());
            } else {
                queryWrapper.orderByDesc("a." + pageVO.getField());
            }
        } else {
            queryWrapper.orderByDesc("a.create_time");
        }
        return TableInfo.ok(this.baseMapper.listSysUserInfoVO(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES addAuthAll(Integer roleId, Integer[] ids) {
        if (null == roleId || 0 == roleId || null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        List<SysUserRole> list = new ArrayList<>();
        SysUserRole sysUserRole = null;
        for (Integer item : ids) {
            sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(item);
            list.add(sysUserRole);
        }
        sysUserRoleService.saveBatch(list);
        return RES.ok();
    }

    @Override
    public RES updateCancelAuth(SysUserRole sysUserRole) {
        if (null == sysUserRole) {
            return RES.no("数据错误");
        }
        if (null == sysUserRole.getUserId() || 0 == sysUserRole.getUserId() || null == sysUserRole.getRoleId() || 0 == sysUserRole.getRoleId()) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, sysUserRole.getUserId());
        queryWrapper.eq(SysUserRole::getRoleId, sysUserRole.getRoleId());
        sysUserRoleMapper.delete(queryWrapper);
        return RES.ok();
    }

    @Override
    public RES updateCancelAuthAll(Long roleId, Long[] userIds) {
        if (null == roleId || 0 == roleId || null == userIds || 0 == userIds.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getRoleId, roleId);
        queryWrapper.in(SysUserRole::getUserId, Arrays.asList(userIds));
        sysUserRoleMapper.delete(queryWrapper);
        return RES.ok();
    }
}
