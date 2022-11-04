package com.asurplus.device.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.device.entity.DevLocInfo;
import com.asurplus.device.mapper.DevLocInfoMapper;
import com.asurplus.device.service.DevLocInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * (DevLocInfo)表服务实现类
 *
 * @author makejava
 * @since 2022-10-19 15:52:30
 */
@Service
public class DevLocInfoServiceImpl extends ServiceImpl<DevLocInfoMapper,DevLocInfo> implements DevLocInfoService {


    @Autowired
    private DevLocInfoService devLocInfoService;

    @Override
    public List<DevLocInfo> list(DevLocInfo devLocInfo) {
        LambdaQueryWrapper<DevLocInfo> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    //主要
    @Override
    public RES treeSelect(DevLocInfo devLocInfo) {
        LambdaQueryWrapper<DevLocInfo> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.select(DevLocInfo::getId, DevLocInfo::getPid, DevLocInfo::getName, DevLocInfo::getSort);
//        queryWrapper.eq(DevLocInfo::getStatus, 0);
        if (StringUtils.isNotBlank(devLocInfo.getName())) {
            queryWrapper.like(DevLocInfo::getName, devLocInfo.getName());
        }
//        queryWrapper.orderByAsc(DevLocInfo::getPid).orderByAsc(DevLocInfo::getSort);
        List<DevLocInfo> list = this.baseMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            return RES.no();
        }
        /**构建nodeList
         * 1栋
         * 1栋，2栋
         */
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        for (DevLocInfo item : list) {
            nodeList.add(new TreeNode<>(String.valueOf(item.getId()), String.valueOf(item.getPid()), item.getName(), String.valueOf(item.getSort())).setExtra(Dict.create()
                    .set("pid", item.getPid())
                    .set("label", item.getName())
            ));
        }
        //nodeList==>treeList
        //treeList:list中的每个元素都是一棵树。根结点不存在
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        return RES.ok(treeList);
    }
}
