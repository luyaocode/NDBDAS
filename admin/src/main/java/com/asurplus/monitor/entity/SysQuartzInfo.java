package com.asurplus.monitor.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务信息对象 sys_quartz_info
 *
 * @author lizhou
 * @date 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_quartz_info")
@ApiModel(value = "SysQuartzInfo对象", description = "定时任务信息")
public class SysQuartzInfo extends Model<SysQuartzInfo> {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "任务类名")
    @TableField("class_name")
    private String className;

    @ApiModelProperty(value = "cron表达式")
    @TableField("cron_expression")
    private String cronExpression;

    @ApiModelProperty(value = "参数")
    @TableField("param")
    private String param;

    @ApiModelProperty(value = "启动状态（0--启动1--停止）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "描述")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "显示顺序(越大越靠前)")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "创建人")
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

    @ApiModelProperty(value = "删除状态（0，正常，1已删除）")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
