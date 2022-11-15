package com.asurplus.gateway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenluyao
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gateway_dict_info")
@ApiModel(value = "GatewayDictInfo对象", description = "网关字典信息表")
public class GatewayDictInfo {
    @ApiModelProperty
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty
    @TableField("item")
    private String item;

    @ApiModelProperty
    @TableField("name")
    private String name;

    @ApiModelProperty
    @TableField("code")
    private Integer code;

    @ApiModelProperty
    @TableField("remark")
    private String remark;
}
