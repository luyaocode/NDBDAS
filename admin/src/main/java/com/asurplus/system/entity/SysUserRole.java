package com.asurplus.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户-角色关系表
 * </p>
 *
 * @author lizhou
 * @since 2021-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_role")
@ApiModel(value="SysUserRole对象", description="用户-角色关系表")
public class SysUserRole extends Model<SysUserRole> {


    @ApiModelProperty(value = "用户角色id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
