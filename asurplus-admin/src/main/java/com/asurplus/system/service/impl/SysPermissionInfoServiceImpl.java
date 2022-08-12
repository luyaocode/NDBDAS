package com.asurplus.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.asurplus.common.consts.CommonConstants;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysPermissionInfo;
import com.asurplus.system.entity.SysRolePermission;
import com.asurplus.system.mapper.SysPermissionInfoMapper;
import com.asurplus.system.mapper.SysRolePermissionMapper;
import com.asurplus.system.service.SysPermissionInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@Service
public class SysPermissionInfoServiceImpl extends ServiceImpl<SysPermissionInfoMapper, SysPermissionInfo> implements SysPermissionInfoService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<SysPermissionInfo> list(SysPermissionInfo sysPermissionInfo) {
        LambdaQueryWrapper<SysPermissionInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(sysPermissionInfo.getName())) {
            queryWrapper.like(SysPermissionInfo::getName, sysPermissionInfo.getName());
        }
        if (StringUtils.isNotBlank(sysPermissionInfo.getType())) {
            queryWrapper.eq(SysPermissionInfo::getType, sysPermissionInfo.getType());
        }
        if (null != sysPermissionInfo.getVisible()) {
            queryWrapper.eq(SysPermissionInfo::getVisible, sysPermissionInfo.getVisible());
        }
        queryWrapper.orderByAsc(SysPermissionInfo::getPid).orderByAsc(SysPermissionInfo::getSort).orderByDesc(SysPermissionInfo::getCreateTime);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public RES getSysPermissionInfo(Integer id) {
        if (null == id || 0 == id) {
            return RES.no("数据错误");
        }
        return RES.ok(this.baseMapper.selectById(id));
    }

    @Override
    public RES add(SysPermissionInfo sysPermissionInfo) {
        if (null == sysPermissionInfo) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysPermissionInfo.getName())) {
            return RES.no("请输入菜单名称");
        }
        if (0 == sysPermissionInfo.getIsFrame() && !StringUtils.startsWithAny(sysPermissionInfo.getPath(), CommonConstants.HTTP, CommonConstants.HTTPS)) {
            return RES.no("地址必须以http(s)://开头");
        }
        // 菜单名称查重
        LambdaQueryWrapper<SysPermissionInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysPermissionInfo::getName, sysPermissionInfo.getName());
        queryWrapper.eq(SysPermissionInfo::getPid, null == sysPermissionInfo.getPid() ? 0 : sysPermissionInfo.getPid());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("菜单名称已存在");
        }
        sysPermissionInfo.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(sysPermissionInfo);
        return RES.ok();
    }

    @Override
    public RES update(SysPermissionInfo sysPermissionInfo) {
        if (null == sysPermissionInfo || null == sysPermissionInfo.getId() || 0 == sysPermissionInfo.getId()) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysPermissionInfo.getName())) {
            return RES.no("请输入菜单名称");
        }
        if (0 == sysPermissionInfo.getIsFrame() && !StringUtils.startsWithAny(sysPermissionInfo.getPath(), CommonConstants.HTTP, CommonConstants.HTTPS)) {
            return RES.no("地址必须以http(s)://开头");
        }
        // 菜单名称查重
        LambdaQueryWrapper<SysPermissionInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysPermissionInfo::getId, sysPermissionInfo.getId());
        queryWrapper.eq(SysPermissionInfo::getName, sysPermissionInfo.getName());
        queryWrapper.eq(SysPermissionInfo::getPid, null == sysPermissionInfo.getPid() ? 0 : sysPermissionInfo.getPid());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("菜单名称已存在");
        }
        sysPermissionInfo.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysPermissionInfo);
        return RES.ok();
    }

    @Override
    public RES delete(Integer id) {
        if (null == id || 0 == id) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysPermissionInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysPermissionInfo::getPid, id);
        Integer rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("存在子菜单,不允许删除");
        }
        LambdaQueryWrapper<SysRolePermission> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(SysRolePermission::getPermissionId, id);
        rows = sysRolePermissionMapper.selectCount(queryWrapper1);
        if (0 < rows) {
            return RES.no("菜单已分配,不允许删除");
        }
        this.baseMapper.deleteById(id);
        return RES.ok();
    }

    @Override
    public RES treeSelect(SysPermissionInfo sysPermissionInfo) {
        LambdaQueryWrapper<SysPermissionInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysPermissionInfo::getId, SysPermissionInfo::getPid, SysPermissionInfo::getName, SysPermissionInfo::getSort);
        if (StringUtils.isNotBlank(sysPermissionInfo.getName())) {
            queryWrapper.like(SysPermissionInfo::getName, sysPermissionInfo.getName());
        }
        queryWrapper.orderByAsc(SysPermissionInfo::getPid).orderByAsc(SysPermissionInfo::getSort);
        List<SysPermissionInfo> list = this.baseMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            return RES.no();
        }
        // 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        for (SysPermissionInfo item : list) {
            nodeList.add(new TreeNode<>(String.valueOf(item.getId()), String.valueOf(item.getPid()), item.getName(), String.valueOf(item.getSort())).setExtra(Dict.create()
                    .set("pid", item.getPid())
                    .set("label", item.getName())
            ));
        }
        // 0表示最顶层的id是0
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        return RES.ok(treeList);
    }

    @Override
    public RES rolePermissionTreeSelect(Integer roleId) {
        RES res = RES.ok();
        LambdaQueryWrapper<SysPermissionInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysPermissionInfo::getId, SysPermissionInfo::getPid, SysPermissionInfo::getName, SysPermissionInfo::getSort);
        queryWrapper.orderByAsc(SysPermissionInfo::getPid).orderByAsc(SysPermissionInfo::getSort);
        List<SysPermissionInfo> list = this.baseMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            return RES.no();
        }
        // 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        for (SysPermissionInfo item : list) {
            nodeList.add(new TreeNode<>(String.valueOf(item.getId()), String.valueOf(item.getPid()), item.getName(), String.valueOf(item.getSort())).setExtra(Dict.create()
                    .set("pid", item.getPid())
                    .set("label", item.getName())
            ));
        }
        // 0表示最顶层的id是0
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        // 查询该角色选中的权限ids
        res.put("menus", treeList);
        res.put("checkedKeys", sysRolePermissionMapper.listMenuIdsByRoleId(roleId));
        return res;
    }
}
