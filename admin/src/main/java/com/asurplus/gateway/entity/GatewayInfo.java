package com.asurplus.gateway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
<<<<<<< HEAD
=======
import io.swagger.models.auth.In;
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gateway_info")
@ApiModel(value = "Gateway对象", description = "网关信息表")
public class GatewayInfo extends Model<GatewayInfo> {
//    主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "网关名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "ip地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "端口号")
    @TableField("port")
    private Integer port;

    @ApiModelProperty(value = "网关类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "创建者")
    @TableField("create_user")
<<<<<<< HEAD
    private String createUser;
=======
    private Integer createUser;
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    @TableField("update_user")
    private String updateUser;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "删除状态")
    @TableField("del_flag")
    private Integer delFlag;


    //    序列化
    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
