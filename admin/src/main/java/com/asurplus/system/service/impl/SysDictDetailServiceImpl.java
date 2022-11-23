package com.asurplus.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.consts.SystemConst;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysDictDetail;
import com.asurplus.system.mapper.SysDictDetailMapper;
import com.asurplus.system.service.SysDictDetailService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 字典配置 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@Service
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetail> implements SysDictDetailService {

    @Override
    public TableInfo list(SysDictDetail sysDictDetail) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<SysDictDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysDictDetail::getDictCode, sysDictDetail.getDictCode());
        if (StringUtils.isNotBlank(sysDictDetail.getCode())) {
            queryWrapper.lambda().like(SysDictDetail::getCode, sysDictDetail.getCode());
        }
        if (StringUtils.isNotBlank(sysDictDetail.getName())) {
            queryWrapper.lambda().like(SysDictDetail::getName, sysDictDetail.getName());
        }
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(SysDictDetail::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES getSysDictDetail(Integer id) {
        if (null == id || 0 == id) {
            return RES.no("请选择需要操作的数据");
        }
        return RES.ok(this.baseMapper.selectById(id));
    }

    @Override
    @CacheEvict(value = SystemConst.SYS_DICT_CACHE, key = "#dictCode")
    public RES add(SysDictDetail sysDictDetail) {
        if (null == sysDictDetail) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysDictDetail.getDictCode())) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysDictDetail.getCode())) {
            return RES.no("请输入字典编码");
        }
        if (StringUtils.isBlank(sysDictDetail.getName())) {
            return RES.no("请输入字典名称");
        }
        // 查重
        LambdaQueryWrapper<SysDictDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictDetail::getDictCode, sysDictDetail.getDictCode());
        queryWrapper.eq(SysDictDetail::getCode, sysDictDetail.getCode());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("该字典编码已经存在");
        }
        sysDictDetail.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(sysDictDetail);
        return RES.ok();
    }

    @Override
    @CacheEvict(value = SystemConst.SYS_DICT_CACHE, key = "#dictCode")
    public RES update(SysDictDetail sysDictDetail) {
        if (null == sysDictDetail || null == sysDictDetail.getId() || 0 == sysDictDetail.getId()) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysDictDetail.getDictCode())) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(sysDictDetail.getCode())) {
            return RES.no("请输入字典编码");
        }
        if (StringUtils.isBlank(sysDictDetail.getName())) {
            return RES.no("请输入字典名称");
        }
        // 查重
        LambdaQueryWrapper<SysDictDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysDictDetail::getId, sysDictDetail.getId());
        queryWrapper.eq(SysDictDetail::getDictCode, sysDictDetail.getDictCode());
        queryWrapper.eq(SysDictDetail::getCode, sysDictDetail.getCode());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("该字典编码已经存在");
        }
        sysDictDetail.setUpdateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(sysDictDetail);
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<SysDictDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysDictDetail::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper);
        return RES.ok();
    }

    @Override
    @Cacheable(value = SystemConst.SYS_DICT_CACHE, key = "#dictCode+':'+#code")
    public String getDictDataByTypeAndValue(String dictCode, String code) {
        return this.baseMapper.getDictDataByTypeAndValue(dictCode, code);
    }

    @Override
    @Cacheable(value = SystemConst.SYS_DICT_CACHE, key = "#dictCode")
//    @CacheEvict(value = SystemConst.SYS_DICT_CACHE, key = "#dictCode")
//    @CacheEvict清除缓存。需要指定缓存的名称
    public List<SysDictDetail> listSysDictDetailByDictCode(String dictCode) {
        return this.baseMapper.listSysDictDetailByDictCode(dictCode);
    }
}
