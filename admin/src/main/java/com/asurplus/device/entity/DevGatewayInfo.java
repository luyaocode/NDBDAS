package com.asurplus.device.entity;

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

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dev_gateway_info")
@ApiModel(value = "DevGatewayInfo对象", description = "设备所属网关信息表")
public class DevGatewayInfo extends Model<DevGatewayInfo> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "序号",width = 15)
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "设备编号",width = 15)
    @ApiModelProperty(value = "设备编号")
    @TableField("dev_id")
    private String devId;


    @Excel(name = "网关ip",width = 15)
    @ApiModelProperty(value = "ip")
    @TableField("ip")
    private String ip;

    @Excel(name = "网关端口",width = 15)
    @ApiModelProperty(value = "port")
    @TableField("port")
    private Integer port;

    @Excel(name = "排序",width = 15)
    @ApiModelProperty(value = "设备排序")
    @TableField("sort")
    private Integer sort;

    @Excel(name = "创建时间",width = 15)
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

}
