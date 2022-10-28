package com.asurplus.device.entity;

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
 * (DevInfo)实体类
 *
 * @author makejava
 * @since 2022-10-19 14:27:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dev_info")
@ApiModel(value = "DevInfo对象", description = "设备信息表")
public class DevInfo extends Model<DevInfo> implements Serializable {
    private static final long serialVersionUID = 857048356854591775L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "设备编号")
    @TableField("dev_id")
    private String devId;

    @ApiModelProperty(value = "设备地理位置编号")
    @TableField("loc_id")
    private String locId;

    @ApiModelProperty(value = "设备名称")
    @TableField("dev_name")
    private String devName;

    @ApiModelProperty(value = "设备状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "删除标记")
    @TableField("del_flag")
    private Integer delFlag;

    @ApiModelProperty(value = "设备信息描述")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "创建者")
    @TableField("create_user")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改者")
    @TableField("update_user")
    private String updateUser;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;

    @Override
    public String toString() {
        return "DevInfo{" +
                "id=" + id +
                ", devId='" + devId + '\'' +
                ", locId='" + locId + '\'' +
                ", devName='" + devName + '\'' +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", remark='" + remark + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}

