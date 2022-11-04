package com.asurplus.system.mapper;

import com.asurplus.system.entity.SysUserInfo;
import com.asurplus.system.vo.SysUserInfoVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author lizhou
 * @since 2021-07-15
 */
public interface SysUserInfoMapper extends BaseMapper<SysUserInfo> {

    IPage<SysUserInfoVO> list(Page<SysUserInfoVO> page, @Param(Constants.WRAPPER) Wrapper<SysUserInfoVO> queryWrapper);

    SysUserInfoVO getSysUserInfoVO(Integer id);

    void updateSysUserInfoAvatar(@Param("id") Integer id, @Param("avatar") String avatar);
}
