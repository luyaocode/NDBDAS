package com.asurplus.tool.service.impl;

import com.asurplus.tool.entity.GenTableColumn;
import com.asurplus.tool.mapper.GenTableColumnMapper;
import com.asurplus.tool.service.GenTableColumnService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-21
 */
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements GenTableColumnService {

    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId) {
        LambdaQueryWrapper<GenTableColumn> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GenTableColumn::getTableId, tableId);
        queryWrapper.orderByAsc(GenTableColumn::getSort);
        return this.baseMapper.selectList(queryWrapper);
    }
}
