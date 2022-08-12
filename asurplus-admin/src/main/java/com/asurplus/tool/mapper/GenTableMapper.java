package com.asurplus.tool.mapper;

import com.asurplus.tool.entity.GenTable;
import com.asurplus.tool.vo.GenTableVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 Mapper 接口
 * </p>
 *
 * @author lizhou
 * @since 2021-07-21
 */
public interface GenTableMapper extends BaseMapper<GenTable> {

    IPage<GenTable> dbList(Page<GenTable> page, @Param(Constants.WRAPPER) Wrapper<GenTable> queryWrapper, @Param("genTable") GenTable genTable);

    List<GenTable> selectDbTableListByNames(String[] tableNames);

    GenTableVO getGenTableVO(Integer id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    GenTableVO selectGenTableByName(String tableName);

    List<GenTableVO> selectGenTableAll();
}
