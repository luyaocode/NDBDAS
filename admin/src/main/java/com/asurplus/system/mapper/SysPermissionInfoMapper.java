package com.asurplus.system.mapper;

import com.asurplus.system.vo.SysMenuVO;
import com.asurplus.system.entity.SysPermissionInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
public interface SysPermissionInfoMapper extends BaseMapper<SysPermissionInfo> {

    List<SysMenuVO> listSysMenuVOAll();

    List<SysMenuVO> listSysMenuVOAllByUserId(Integer userId);
}
