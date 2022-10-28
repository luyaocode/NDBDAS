package com.asurplus.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysDeptInfo;
import com.asurplus.system.entity.SysUserInfo;
import com.asurplus.system.mapper.SysDeptInfoMapper;
import com.asurplus.system.mapper.SysUserInfoMapper;
import com.asurplus.system.service.SysDeptInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@Service
public class SysDeptInfoServiceImpl extends ServiceImpl<SysDeptInfoMapper, SysDeptInfo> implements SysDeptInfoService {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    @Override
    public List<SysDeptInfo> list(SysDeptInfo sysDeptInfo) {
        LambdaQueryWrapper<SysDeptInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != sysDeptInfo.getId() && 0 < sysDeptInfo.getId()) {
            queryWrapper.ne(SysDeptInfo::getId, sysDeptInfo.getId());
            queryWrapper.ne(SysDeptInfo::getPid, sysDeptInfo.getId());
        }
        if (StringUtils.isNotBlank(sysDeptInfo.getName())) {
            queryWrapper.like(SysDeptInfo::getName, sysDeptInfo.getName());
        }
        if (null != sysDeptInfo.getStatus()) {
            queryWrapper.eq(SysDeptInfo::getStatus, sysDeptInfo.getStatus());
        }
        queryWrapper.orderByAsc(SysDeptInfo::getPid).orderByAsc(SysDeptInfo::getSort).orderByDesc(SysDeptInfo::getCreateTime);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public RES getSysDeptInfo(Integer id) {
        return RES.ok(this.baseMapper.selectById(id));
    }

    @Override
    public RES add(SysDeptInfo sysDeptInfo) {
        if (null == sysDeptInfo) {
            return RES.no("数据错误");
        }
        if (null == sysDeptInfo.getName()) {
            return RES.no("请输入部门名称");
        }
        LambdaQueryWrapper<SysDeptInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDeptInfo::getName, sysDeptInfo.getName());
        queryWrapper.eq(SysDeptInfo::getPid, null == sysDeptInfo.getPid() ? 0 : sysDeptInfo.getPid());
        int count = this.baseMapper.selectCount(queryWrapper);
        if (0 < count) {
            return RES.no("部门名称已存在");
        }
        sysDeptInfo.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(sysDeptInfo);
        return RES.ok();
    }

    @Override
    public RES update(SysDeptInfo sysDeptInfo) {
        if (null == sysDeptInfo || null == sysDeptInfo.getId() || 0 == sysDeptInfo.getId()) {
            return RES.no("数据错误");
        }
        if (null == sysDeptInfo.getName()) {
            return RES.no("请输入部门名称");
        }
        LambdaQueryWrapper<SysDeptInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysDeptInfo::getId, sysDeptInfo.getId());
        queryWrapper.eq(SysDeptInfo::getName, sysDeptInfo.getName());
        queryWrapper.eq(SysDeptInfo::getPid, null == sysDeptInfo.getPid() ? 0 : sysDeptInfo.getPid());
        int count = this.baseMapper.selectCount(queryWrapper);
        if (0 < count) {
            return RES.no("部门名称已存在");
        }
        sysDeptInfo.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysDeptInfo);
        // 启用状态，所有父级启用
        if (0 == sysDeptInfo.getStatus() && 0 < sysDeptInfo.getPid()) {
            LambdaQueryWrapper<SysDeptInfo> queryWrapper1;
            // 父级ids
            Set<Integer> pids = new HashSet<>();
            pids.add(sysDeptInfo.getPid());
            // 父级数据
            List<SysDeptInfo> pList = new ArrayList<>();
            // 查询出来的数据
            List<SysDeptInfo> list = null;
            int i = 0;
            while (i == 0) {
                queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.in(SysDeptInfo::getId, pids);
                list = this.baseMapper.selectList(queryWrapper1);
                if (CollectionUtil.isEmpty(list)) {
                    break;
                }
                pList.addAll(list);
                pids = new HashSet<>();
                for (SysDeptInfo item : list) {
                    pids.add(item.getPid());
                }
            }
            if (CollectionUtil.isNotEmpty(pList)) {
                for (SysDeptInfo item : pList) {
                    item.setStatus(0);
                }
                this.updateBatchById(pList);
            }
        }
        // 禁用状态，所有子级禁用
        if (1 == sysDeptInfo.getStatus()) {
            LambdaQueryWrapper<SysDeptInfo> queryWrapper1;
            // 父级ids
            Set<Integer> pids = new HashSet<>();
            pids.add(sysDeptInfo.getId());
            // 父级数据
            List<SysDeptInfo> pList = new ArrayList<>();
            // 查询出来的数据
            List<SysDeptInfo> list = null;
            int i = 0;
            while (i == 0) {
                queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.in(SysDeptInfo::getPid, pids);
                list = this.baseMapper.selectList(queryWrapper1);
                if (CollectionUtil.isEmpty(list)) {
                    break;
                }
                pList.addAll(list);
                pids = new HashSet<>();
                for (SysDeptInfo item : list) {
                    pids.add(item.getId());
                }
            }
            if (CollectionUtil.isNotEmpty(pList)) {
                for (SysDeptInfo item : pList) {
                    item.setStatus(1);
                }
                this.updateBatchById(pList);
            }
        }
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysDeptInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysDeptInfo::getPid, Arrays.asList(ids));
        int count = this.baseMapper.selectCount(queryWrapper);
        if (0 < count) {
            return RES.no("存在下级部门，不允许删除");
        }
        LambdaQueryWrapper<SysUserInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(SysUserInfo::getDeptId, Arrays.asList(ids));
        Integer rows = sysUserInfoMapper.selectCount(queryWrapper1);
        if (null != rows && 0 < rows) {
            return RES.no("部门存在用户，不允许删除");
        }
        LambdaQueryWrapper<SysDeptInfo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(SysDeptInfo::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper2);
        return RES.ok();
    }

    /**
     * SysDeptInfo::getId
     *      实例化对象SysDeptInfo,调用其方法getId()
     */
    @Override
    public RES treeSelect(SysDeptInfo sysDeptInfo) {
        LambdaQueryWrapper<SysDeptInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.select(SysDeptInfo::getId, SysDeptInfo::getPid, SysDeptInfo::getName, SysDeptInfo::getSort);

//        ==》sysDeptInfo.getStatus=0，表示可用状态
        queryWrapper.eq(SysDeptInfo::getStatus, 0);
        if (StringUtils.isNotBlank(sysDeptInfo.getName())) {
            queryWrapper.like(SysDeptInfo::getName, sysDeptInfo.getName());
        }
        queryWrapper.orderByAsc(SysDeptInfo::getPid).orderByAsc(SysDeptInfo::getSort);
        List<SysDeptInfo> list = this.baseMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            return RES.no();
        }
        // 构建node列表
        /**
         * public class TreeNode<T> implements Node<T> {
         *     private T id;                        --结点id
         *     private T parentId;                  --结点pid
         *     private CharSequence name;           --结点名称
         *     private Comparable<?> weight = 0;
         *     private Map<String, Object> extra;
         *
         *     public TreeNode() {
         *     }
         *     ...
         * }
         */
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        for (SysDeptInfo item : list) {
            nodeList.add(new TreeNode<>(String.valueOf(item.getId()), String.valueOf(item.getPid()), item.getName(), String.valueOf(item.getSort())).setExtra(Dict.create()
                    .set("pid", item.getPid())
                    .set("label", item.getName())
            ));
        }
        // 0表示最顶层的id是0
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        return RES.ok(treeList);
    }
}
