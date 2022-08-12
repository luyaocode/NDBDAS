package com.asurplus.monitor.entity;

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
 * <p>
 * 登录日志
 * </p>
 *
 * @author lizhou
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_login_log")
@ApiModel(value = "SysLoginLog对象", description = "登录日志")
public class SysLoginLog extends Model<SysLoginLog> {


    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "账户", width = 15)
    @ApiModelProperty(value = "账户")
    @TableField("account")
    private String account;

    @Excel(name = "IP", width = 15)
    @ApiModelProperty(value = "IP地址")
    @TableField("ip")
    private String ip;

    @Excel(name = "位置", width = 30)
    @ApiModelProperty(value = "地理位置")
    @TableField("location")
    private String location;

    @Excel(name = "浏览器", width = 15)
    @ApiModelProperty(value = "浏览器")
    @TableField("browser")
    private String browser;

    @Excel(name = "操作系统", width = 15)
    @ApiModelProperty(value = "操作系统")
    @TableField("os")
    private String os;

    @Excel(name = "设备", width = 15)
    @ApiModelProperty(value = "设备")
    @TableField("device")
    private String device;

    @Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @Excel(name = "状态", width = 15, dict = "is_success")
    @ApiModelProperty(value = "状态（0--成功1--失败）")
    @TableField("status")
    private Integer status;

    @Excel(name = "创建时间", width = 30)
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
