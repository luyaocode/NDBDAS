package com.asurplus.device.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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

    @Excel(name = "楼层编号",width = 15)
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "父结点",width = 15)
    @ApiModelProperty(value = "楼层编号")
    @TableField("pid")
    private String pid;

    @Excel(name = "楼层名称",width = 15)
    @ApiModelProperty(value = "楼层名称")
    @TableField("name")
    private String name;

    @Excel(name = "次序",width = 15)
    @ApiModelProperty(value = "次序")
    @TableField("sort")
    private Integer sort;

}

