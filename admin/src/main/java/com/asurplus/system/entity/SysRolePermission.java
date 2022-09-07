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
 * 角色-权限关系表
 * </p>
 *
 * @author lizhou
 * @since 2021-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_permission")
@ApiModel(value="SysRolePermission对象", description="角色-权限关系表")
public class SysRolePermission extends Model<SysRolePermission> {


    @ApiModelProperty(value = "角色权限id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "权限id")
    @TableField("permission_id")
    private Integer permissionId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
