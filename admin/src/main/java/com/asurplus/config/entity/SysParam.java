package com.asurplus.config.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统参数配置对象 sys_param
 *
 * @author lizhou
 * @date 2021-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_param")
@ApiModel(value = "SysParam对象", description = "系统参数配置")
public class SysParam extends Model<SysParam> {


    @ApiModelProperty(value = "参数主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
    @TableField("name")
    private String name;

    @Excel(name = "参数键名", width = 30)
    @ApiModelProperty(value = "参数键名")
    @TableField("param_key")
    private String paramKey;

    @Excel(name = "参数键值", width = 30)
    @ApiModelProperty(value = "参数键值")
    @TableField("param_value")
    private String paramValue;

    @Excel(name = "系统内置", width = 15, dict = "is_builtIn")
    @ApiModelProperty(value = "系统内置（0-是 1-否）")
    @TableField("type")
    private Integer type;

    @Excel(name = "备注", width = 30)
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


    @ApiModelProperty(value = "创建者")
    @TableField("create_user")
    private Integer createUser;

    @Excel(name = "创建时间", width = 30, format = "yyyy-MM-dd HH:mm:ss")
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
        return this.id;
    }
}
