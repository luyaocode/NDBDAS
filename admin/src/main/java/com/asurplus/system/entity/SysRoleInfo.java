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
 * 角色信息表
 * </p>
 *
 * @author lizhou
 * @since 2021-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_info")
@ApiModel(value="SysRoleInfo对象", description="角色信息表")
public class SysRoleInfo extends Model<SysRoleInfo> {


    @ApiModelProperty(value = "角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "角色标志")
    @TableField("sign")
    private String sign;

    @ApiModelProperty(value = "角色描述")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "状态（0-正常1-停用）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "排序（越大越靠前）")
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
        return this.id;
    }

}
