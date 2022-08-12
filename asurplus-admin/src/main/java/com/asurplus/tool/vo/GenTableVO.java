package com.asurplus.tool.vo;

import com.asurplus.tool.entity.GenTable;
import com.asurplus.tool.entity.GenTableColumn;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GenTableVO extends GenTable {

    /**
     * 列
     */
    @TableField(exist = false)
    private List<GenTableColumn> columns;

    /**
     * 主键信息
     */
    @TableField(exist = false)
    private GenTableColumn pkColumn;

    /**
     * 子表信息
     */
    @TableField(exist = false)
    private GenTableVO subTable;

    /**
     * 树编码字段
     */
    @TableField(exist = false)
    private String treeCode;

    /**
     * 树父编码字段
     */
    @TableField(exist = false)
    private String treeParentCode;

    /**
     * 树名称字段
     */
    @TableField(exist = false)
    private String treeName;

    /**
     * 上级菜单ID字段
     */
    @TableField(exist = false)
    private String parentMenuId;

    /**
     * 上级菜单名称字段
     */
    @TableField(exist = false)
    private String parentMenuName;

    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params;
}
