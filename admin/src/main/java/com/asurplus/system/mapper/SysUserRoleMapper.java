package com.asurplus.system.mapper;

import com.asurplus.system.entity.SysRoleInfo;
import com.asurplus.system.entity.SysUserRole;
import com.asurplus.system.vo.SysRoleInfoAuthVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户-角色关系表 Mapper 接口
 * </p>
 *
 * @author lizhou
 * @since 2021-07-16
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据用户ID查询拥有的角色码
     * @param userId
     * @return
     */
    List<String> listRoleSignByUserId(String userId);

    /**
     * 根据用户ID查询拥有的角色ids
     * @param userId
     * @return
     */
    List<Integer> listRoleIdsByUserId(Integer userId);

    /**
     * 根据用户ID查询拥有的角色name
     * @param userId
     * @return
     */
    List<String> listRoleNameByUserId(Integer userId);

    /**
     * 根据用户ID查询是否赋予角色
     * @param userId
     * @return
     */
    List<SysRoleInfoAuthVO> listRoleInfo(Integer userId);
}
