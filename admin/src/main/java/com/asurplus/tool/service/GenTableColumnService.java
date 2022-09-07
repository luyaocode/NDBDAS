package com.asurplus.tool.service;

import com.asurplus.tool.entity.GenTableColumn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-21
 */
public interface GenTableColumnService extends IService<GenTableColumn> {

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    public List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId);
}
