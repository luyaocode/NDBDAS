package com.asurplus.tool.mapper;

import com.asurplus.tool.entity.GenTableColumn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 Mapper 接口
 * </p>
 *
 * @author lizhou
 * @since 2021-07-21
 */
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {

    List<GenTableColumn> selectDbTableColumnsByName(String tableName);
}
