package com.asurplus.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_permission_info")
@ApiModel(value = "SysPermissionInfo对象", description = "菜单权限表")
public class SysPermissionInfo extends Model<SysPermissionInfo> {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "父菜单ID")
    @TableField("pid")
    private Integer pid;

    @ApiModelProperty(value = "显示顺序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "是否为外链（0是 1否）")
    @TableField("is_frame")
    private Integer isFrame;

    @ApiModelProperty(value = "是否缓存（0缓存 1不缓存）")
    @TableField("is_cache")
    private Integer isCache;

    @ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    @TableField("visible")
    private Integer visible;

    @ApiModelProperty(value = "权限标识")
    @TableField("sign")
    private String sign;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "创建者")
    @TableField("create_user")
    private Integer createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
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
