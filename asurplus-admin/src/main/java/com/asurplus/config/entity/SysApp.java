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
 * app版本信息对象 sys_app
 *
 * @author lizhou
 * @date 2021-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_app")
@ApiModel(value = "SysApp对象", description = "app版本信息")
public class SysApp extends Model<SysApp> {


    @ApiModelProperty(value = "")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "APP名称", width = 15)
    @ApiModelProperty(value = "APP名称")
    @TableField("app_name")
    private String appName;

    @Excel(name = "版本名称", width = 15)
    @ApiModelProperty(value = "版本名称")
    @TableField("version_name")
    private String versionName;

    @Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    @TableField("version_code")
    private String versionCode;

    @Excel(name = "版本描述", width = 15)
    @ApiModelProperty(value = "版本描述")
    @TableField("version_info")
    private String versionInfo;

    @Excel(name = "强制更新", width = 15, dict = "is_force")
    @ApiModelProperty(value = "是否强制更新（0-否1-是）")
    @TableField("is_force")
    private Integer isForce;

    @Excel(name = "IOS下载链接", width = 30)
    @ApiModelProperty(value = "【IOS】下载地址")
    @TableField("ios")
    private String ios;

    @Excel(name = "Android下载链接", width = 30)
    @ApiModelProperty(value = "【Android】下载地址")
    @TableField("android")
    private String android;

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
