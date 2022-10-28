package com.asurplus.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (DevLocInfo)实体类
 *
 * @author makejava
 * @since 2022-10-19 15:52:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dev_loc_info")
@ApiModel(value = "DevLocInfo对象", description = "设备地理位置信息表")
public class DevLocInfo implements Serializable {
    private static final long serialVersionUID = -56442928521715641L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "楼层编号")
    @TableField("pid")
    private String pid;

    @ApiModelProperty(value = "楼层名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "序号")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "楼编号")
    @TableField("bu_id")
    private Integer buId;

    @ApiModelProperty(value = "层编号")
    @TableField("fl_id")
    private Integer flId;

    @ApiModelProperty(value = "房间编号")
    @TableField("ro_id")
    private Integer roId;

    @ApiModelProperty(value = "关联设备编号")
    @TableField("link_dev_id")
    private String linkDevId;

}

