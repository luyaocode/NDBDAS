package com.asurplus.system.service;

import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysUserInfo;
import com.asurplus.system.vo.LoginDTO;
import com.asurplus.system.vo.RouterVO;
import com.asurplus.system.vo.SysMenuVO;

import java.util.List;

public interface LoginService {

    /**
     * 执行登录
     *
     * @param loginDTO
     * @return
     */
    RES login(LoginDTO loginDTO);

    /**
     * 获取用户信息（角色，权限）
     *
     * @return
     */
    RES getInfo();

    /**
     * 获取左侧菜单
     *
     * @return
     */
    RES getRouters();

    /**
     * 从session中获取用户信息
     *
     * @return
     */
    SysUserInfo getUserInfoFromSession();

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    SysUserInfo getUserInfo(Integer id);

    /**
     * 查询用户的菜单数据
     *
     * @return
     */
    List<SysMenuVO> listRouters();

    /**
     * 构建前端需要的菜单数据格式
     *
     * @param menus
     * @return
     */
    List<RouterVO> buildMenus(List<SysMenuVO> menus);

    /**
     * 登出
     *
     * @return
     */
    RES logout();
}
