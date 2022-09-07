package com.asurplus.system.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author lizhou
 * @since 2021-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_info")
@ApiModel(value = "SysUserInfo对象", description = "用户信息表")
public class SysUserInfo extends Model<SysUserInfo> {


    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "所属部门")
    @TableField("dept_id")
    private Integer deptId;

    @Excel(name = "登录账户", width = 15)
    @ApiModelProperty(value = "登录账户")
    @TableField("account")
    private String account;

    @ApiModelProperty(value = "登录密码")
    @TableField("password")
    private String password;

    @Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @Excel(name = "性别", width = 15, dict = "sex")
    @ApiModelProperty(value = "性别（0--未知1--男2--女）")
    @TableField("sex")
    private Integer sex;

    @Excel(name = "邮箱地址", width = 30)
    @ApiModelProperty(value = "邮箱地址")
    @TableField("email")
    private String email;

    @Excel(name = "性别", width = 15, dict = "status")
    @ApiModelProperty(value = "状态（0--正常1--冻结）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建者")
    @TableField("create_user")
    private Integer createUser;

    @Excel(name = "创建时间", width = 30)
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    @TableField("update_user")
    private Integer updateUser;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "删除状态（0--未删除1--已删除）")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
