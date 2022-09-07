package com.asurplus.system.entity;

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
 * 部门信息表
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dept_info")
@ApiModel(value="SysDeptInfo对象", description="部门信息表")
public class SysDeptInfo extends Model<SysDeptInfo> {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "上级部门")
    @TableField("pid")
    private Integer pid;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "联系人")
    @TableField("link_user")
    private String linkUser;

    @ApiModelProperty(value = "联系电话")
    @TableField("link_phone")
    private String linkPhone;

    @ApiModelProperty(value = "排序（值越大，越靠前）")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "状态（0--正常1--停用）")
    @TableField("status")
    private Integer status;

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
        return this.id;
    }

}
