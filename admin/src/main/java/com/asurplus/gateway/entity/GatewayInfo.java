package com.asurplus.gateway.entity;

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

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gateway_info")
@ApiModel(value = "Gateway对象", description = "网关信息表")
public class GatewayInfo extends Model<GatewayInfo> {
//    主键
    @ApiModelProperty(value = "记录标识id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "ip地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "端口号")
    @TableField("port")
    private Integer port;

    //    序列化
    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
