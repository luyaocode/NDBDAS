package com.asurplus.tool.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 代码生成业务表字段
 * </p>
 *
 * @author lizhou
 * @since 2021-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gen_table_column")
@ApiModel(value = "GenTableColumn对象", description = "代码生成业务表字段")
public class GenTableColumn extends Model<GenTableColumn> {


    @ApiModelProperty(value = "编号")
    @TableId(value = "column_id", type = IdType.AUTO)
    private Integer columnId;

    @ApiModelProperty(value = "归属表编号")
    @TableField("table_id")
    private Integer tableId;

    @ApiModelProperty(value = "列名称")
    @TableField("column_name")
    private String columnName;

    @ApiModelProperty(value = "列描述")
    @TableField("column_comment")
    private String columnComment;

    @ApiModelProperty(value = "列类型")
    @TableField("column_type")
    private String columnType;

    @ApiModelProperty(value = "JAVA类型")
    @TableField("java_type")
    private String javaType;

    @ApiModelProperty(value = "JAVA字段名")
    @TableField("java_field")
    private String javaField;

    @ApiModelProperty(value = "是否主键（1是）")
    @TableField("is_pk")
    private String isPk;

    @ApiModelProperty(value = "是否自增（1是）")
    @TableField("is_increment")
    private String isIncrement;

    @ApiModelProperty(value = "是否必填（1是）")
    @TableField("is_required")
    private String isRequired;

    @ApiModelProperty(value = "是否为插入字段（1是）")
    @TableField("is_insert")
    private String isInsert;

    @ApiModelProperty(value = "是否编辑字段（1是）")
    @TableField("is_edit")
    private String isEdit;

    @ApiModelProperty(value = "是否列表字段（1是）")
    @TableField("is_list")
    private String isList;

    @ApiModelProperty(value = "是否查询字段（1是）")
    @TableField("is_query")
    private String isQuery;

    @ApiModelProperty(value = "查询方式（等于、不等于、大于、小于、范围）")
    @TableField("query_type")
    private String queryType;

    @ApiModelProperty(value = "显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    @TableField("html_type")
    private String htmlType;

    @ApiModelProperty(value = "字典类型")
    @TableField("dict_type")
    private String dictType;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "创建者")
    @TableField("create_user")
    private Integer createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    @TableField("update_user")
    private Integer updateUser;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "删除状态（0--未删除1--已删除）")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.columnId;
    }

}
