package com.asurplus.tool.service;

import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.asurplus.tool.entity.GenTable;
import com.asurplus.tool.vo.GenTableUpdateVO;
import com.asurplus.tool.vo.GenTableVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-21
 */
public interface GenTableService extends IService<GenTable> {

    TableInfo list(GenTable genTable);

    TableInfo dbList(GenTable genTable);

    RES saveImportTable(String tables);

    RES previewCode(Integer tableId);

    GenTableVO selectGenTableById(Integer id);

    /**
     * 查询所有表信息
     */
    List<GenTableVO> selectGenTableAll();

    /**
     * 批量生成代码下载
     */
    byte[] downloadCode(String[] tableNames);

    /**
     * 生成代码（自定义路径）
     *
     * @param tableName 表名称
     * @return 数据
     */
    void generatorCode(String tableName);

    /**
     * 同步数据库
     */
    void updateSynchDb(String tableName);

    RES update(GenTableUpdateVO genTable);

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    RES validateEdit(GenTableUpdateVO genTable);

    RES delete(Integer[] ids);
}
