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
 * 设备电参量信息表
 *
 * @author luyao
 * @Data 注解的主要作用是提高代码的简洁，使用这个注解可以省去实体类中大量的get()、 set()、 toString()等方法。
 * @EqualsAndHashCode 修复@Data可能导致的一些问题
 * @TableName 注解主要是实现实体类型和数据库中的表实现映射。
 * @ApiModel 可以展示出请求参数的含义和返回参数的含义。
 * value属性
 * 这个属性，提供的是类的一个备用名。如果我们不设置，那么默认情况下，将使用的是class类的名字。
 * description属性
 *      对于类，提供一个详细的描述信息
 * @ApiModelProperty value属性
 * 属性简要说明
 * @TableId 这个注解表示表的主键
 * 接受两个参数
 * value = 主键列名
 * type = 主键类型
 * @TableLogic 注解表示逻辑删除
 * 效果：在字段上加上这个注解再执行BaseMapper的删除方法时，删除方法会变成修改
 * @since 2022/9/13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dev_epara_info_temp")
@ApiModel(value = "DevEParaInfo对象", description = "电参量信息表")
public class DevEParaInfo extends Model<DevEParaInfo> {
    @ApiModelProperty(value = "数据项标识id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "设备编号")
    @TableField("dev_id")
    private String devId;

    @ApiModelProperty(value = "设备名称")
    @TableField("dev_name")
    private String devName;

    @ApiModelProperty(value = "设备描述")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "数据创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "频率")
    @TableField("freq")
    private float freq;

    @ApiModelProperty(value = "A相电压")
    @TableField("volt_a")
    private float voltA;

    @ApiModelProperty(value = "B相电压")
    @TableField("volt_b")
    private float voltB;

    @ApiModelProperty(value = "C相电压")
    @TableField("volt_c")
    private float voltC;

    @ApiModelProperty(value = "A相电流")
    @TableField("curr_a")
    private float currA;

    @ApiModelProperty(value = "B相电流")
    @TableField("curr_b")
    private float currB;

    @ApiModelProperty(value = "C相电流")
    @TableField("curr_c")
    private float currC;

    @ApiModelProperty(value = "A相有功功率")
    @TableField("acti_pow_a")
    private float actiPowA;

    @ApiModelProperty(value = "B相有功功率")
    @TableField("acti_pow_b")
    private float actiPowB;

    @ApiModelProperty(value = "C相有功功率")
    @TableField("acti_pow_c")
    private float actiPowC;

    @ApiModelProperty(value = "总有功功率")
    @TableField("acti_pow_total")
    private float actiPowTotal;

    @ApiModelProperty(value = "A相无功功率")
    @TableField("watt_pow_a")
    private float wattPowA;

    @ApiModelProperty(value = "B相无功功率")
    @TableField("watt_pow_b")
    private float wattPowB;

    @ApiModelProperty(value = "C相无功功率")
    @TableField("watt_pow_c")
    private float wattPowC;

    @ApiModelProperty(value = "总无功功率")
    @TableField("watt_pow_total")
    private float wattPowTotal;

    @ApiModelProperty(value = "A相视在功率")
    @TableField("appr_pow_a")
    private float apprPowA;

    @ApiModelProperty(value = "B相视在功率")
    @TableField("appr_pow_b")
    private float apprPowB;

    @ApiModelProperty(value = "C相视在功率")
    @TableField("appr_pow_c")
    private float apprPowC;

    @ApiModelProperty(value = "总视在功率")
    @TableField("appr_pow_total")
    private float apprPowTotal;

    @ApiModelProperty(value = "A相功率因数")
    @TableField("pow_fac_a")
    private float powFacA;

    @ApiModelProperty(value = "B相功率因数")
    @TableField("pow_fac_b")
    private float powFacB;

    @ApiModelProperty(value = "C相功率因数")
    @TableField("pow_fac_c")
    private float powFacC;



//    序列化
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
