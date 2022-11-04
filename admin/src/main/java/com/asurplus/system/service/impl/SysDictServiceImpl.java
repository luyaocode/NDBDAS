package com.asurplus.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysDict;
import com.asurplus.system.entity.SysDictDetail;
import com.asurplus.system.mapper.SysDictMapper;
import com.asurplus.system.service.SysDictDetailService;
import com.asurplus.system.service.SysDictService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 字典管理 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Autowired
    private SysDictDetailService sysDictDetailService;

    @Override
    public TableInfo list(SysDict sysDict) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysDict.getCode())) {
            queryWrapper.lambda().like(SysDict::getCode, sysDict.getCode());
        }
        if (StringUtils.isNotBlank(sysDict.getName())) {
            queryWrapper.lambda().like(SysDict::getName, sysDict.getName());
        }
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(SysDict::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES listSelect() {
        LambdaQueryWrapper<SysDict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysDict::getId, SysDict::getCode, SysDict::getName);
        return RES.ok(this.baseMapper.selectList(queryWrapper));
    }

    @Override
    public RES getSysDict(Integer id) {
        if (null == id || 0 == id) {
            return RES.no("请选择需要操作的数据");
        }
        return RES.ok(this.baseMapper.selectById(id));
    }

    @Override
    public RES add(SysDict sysDict) {
        if (null == sysDict) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysDict.getCode())) {
            return RES.no("请输入字典编码");
        }
        if (StringUtils.isBlank(sysDict.getName())) {
            return RES.no("请输入字典名称");
        }
        // 查重
        LambdaQueryWrapper<SysDict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDict::getCode, sysDict.getCode());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("该字典编码已经存在");
        }
        sysDict.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(sysDict);
        return RES.ok();
    }

    @Override
    public RES update(SysDict sysDict) {
        if (null == sysDict || null == sysDict.getId() || 0 == sysDict.getId()) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysDict.getCode())) {
            return RES.no("请输入字典编码");
        }
        if (StringUtils.isBlank(sysDict.getName())) {
            return RES.no("请输入字典名称");
        }
        // 查重
        LambdaQueryWrapper<SysDict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysDict::getId, sysDict.getId());
        queryWrapper.eq(SysDict::getCode, sysDict.getCode());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("该字典编码已经存在");
        }
        // 查询系统数据
        SysDict dbObj = this.baseMapper.selectById(sysDict.getId());
        // 修改数据
        sysDict.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysDict);
        // 修改了code
        if (!dbObj.getCode().equals(sysDict.getCode())) {
            LambdaQueryWrapper<SysDictDetail> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(SysDictDetail::getDictCode, dbObj.getCode());
            List<SysDictDetail> list = sysDictDetailService.list(queryWrapper1);
            if (CollectionUtil.isNotEmpty(list)) {
                for (SysDictDetail item : list) {
                    item.setDictCode(sysDict.getCode());
                }
                sysDictDetailService.updateBatchById(list);
            }
        }
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysDict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysDict::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper);
        return RES.ok();
    }

    @Override
    public RES optionselect() {
        LambdaQueryWrapper<SysDict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysDict::getId, SysDict::getName, SysDict::getCode);
        queryWrapper.orderByDesc(SysDict::getCreateTime);
        return RES.ok(this.baseMapper.selectList(queryWrapper));
    }
}
