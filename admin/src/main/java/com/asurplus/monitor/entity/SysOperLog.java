package com.asurplus.monitor.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志记录对象 sys_oper_log
 *
 * @author lizhou
 * @date 2021-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_oper_log")
@ApiModel(value = "SysOperLog对象", description = "操作日志记录")
public class SysOperLog extends Model<SysOperLog> {


    @ApiModelProperty(value = "日志主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Excel(name = "模块标题", width = 15)
    @ApiModelProperty(value = "模块标题")
    @TableField("title")
    private String title;

    @Excel(name = "业务类型", width = 15, dict = "oper_type")
    @ApiModelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    @TableField("type")
    private Integer type;

    @Excel(name = "方法名称", width = 30)
    @ApiModelProperty(value = "方法名称")
    @TableField("fun")
    private String fun;

    @Excel(name = "请求方式", width = 15)
    @ApiModelProperty(value = "请求方式")
    @TableField("method")
    private String method;

    @Excel(name = "请求URL", width = 30)
    @ApiModelProperty(value = "请求URL")
    @TableField("url")
    private String url;

    @Excel(name = "主机地址", width = 15)
    @ApiModelProperty(value = "主机地址")
    @TableField("ip")
    private String ip;

    @Excel(name = "操作地点", width = 30)
    @ApiModelProperty(value = "操作地点")
    @TableField("location")
    private String location;

    @Excel(name = "请求参数", width = 30)
    @ApiModelProperty(value = "请求参数")
    @TableField("param")
    private String param;

    @Excel(name = "返回参数", width = 30)
    @ApiModelProperty(value = "返回参数")
    @TableField("result")
    private String result;

    @Excel(name = "操作状态", width = 15, dict = "is_success")
    @ApiModelProperty(value = "操作状态（0正常 1异常）")
    @TableField("status")
    private Integer status;

    @Excel(name = "错误消息", width = 15)
    @ApiModelProperty(value = "错误消息")
    @TableField("error_msg")
    private String errorMsg;

    @Excel(name = "耗时(ms)", width = 15)
    @ApiModelProperty(value = "耗时")
    @TableField("spend_time")
    private Long spendTime;

    @Excel(name = "操作人员", width = 15)
    @ApiModelProperty(value = "操作人员")
    @TableField("oper_name")
    private String operName;

    @Excel(name = "操作时间", width = 30, format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
    @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
