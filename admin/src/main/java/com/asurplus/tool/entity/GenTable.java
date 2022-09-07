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
 * 代码生成业务表
 * </p>
 *
 * @author lizhou
 * @since 2021-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gen_table")
@ApiModel(value = "GenTable对象", description = "代码生成业务表")
public class GenTable extends Model<GenTable> {


    @ApiModelProperty(value = "编号")
    @TableId(value = "table_id", type = IdType.AUTO)
    private Integer tableId;

    @ApiModelProperty(value = "表名称")
    @TableField("table_name")
    private String tableName;

    @ApiModelProperty(value = "表描述")
    @TableField("table_comment")
    private String tableComment;

    @ApiModelProperty(value = "关联子表的表名")
    @TableField("sub_table_name")
    private String subTableName;

    @ApiModelProperty(value = "子表关联的外键名")
    @TableField("sub_table_fk_name")
    private String subTableFkName;

    @ApiModelProperty(value = "实体类名称")
    @TableField("class_name")
    private String className;

    @ApiModelProperty(value = "使用的模板（crud单表操作 tree树表操作）")
    @TableField("tpl_category")
    private String tplCategory;

    @ApiModelProperty(value = "生成包路径")
    @TableField("package_name")
    private String packageName;

    @ApiModelProperty(value = "生成模块名")
    @TableField("module_name")
    private String moduleName;

    @ApiModelProperty(value = "生成业务名")
    @TableField("business_name")
    private String businessName;

    @ApiModelProperty(value = "生成功能名")
    @TableField("function_name")
    private String functionName;

    @ApiModelProperty(value = "生成功能作者")
    @TableField("function_author")
    private String functionAuthor;

    @ApiModelProperty(value = "生成代码方式（0zip压缩包 1自定义路径）")
    @TableField("gen_type")
    private String genType;

    @ApiModelProperty(value = "生成路径（不填默认项目路径）")
    @TableField("gen_path")
    private String genPath;

    @ApiModelProperty(value = "其它生成选项")
    @TableField("options")
    private String options;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

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
        return this.tableId;
    }

}
