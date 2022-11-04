package com.asurplus.system.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.asurplus.common.consts.CommonConstants;
import com.asurplus.common.enums.LoginEnums;
import com.asurplus.common.exception.CustomException;
import com.asurplus.common.redis.RedisConst;
import com.asurplus.common.redis.RedisUtil;
import com.asurplus.common.satoken.SaTokenSessionConst;
import com.asurplus.common.utils.PasswordUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.common.utils.ServletUtils;
import com.asurplus.monitor.service.SysLoginLogService;
import com.asurplus.system.entity.SysUserInfo;
import com.asurplus.system.mapper.SysPermissionInfoMapper;
import com.asurplus.system.service.LoginService;
import com.asurplus.system.service.SysUserInfoService;
import com.asurplus.system.vo.LoginDTO;
import com.asurplus.system.vo.MetaVo;
import com.asurplus.system.vo.RouterVO;
import com.asurplus.system.vo.SysMenuVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserInfoService sysUserInfoService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysPermissionInfoMapper sysPermissionInfoMapper;
    @Autowired
    private SysLoginLogService sysLoginLogService;
    @Autowired
    private StpInterface stpInterface;

    @Override
    public RES login(LoginDTO loginDTO) {
        if (null == loginDTO) {
            throw new CustomException("登录失败");
        }
        if (StringUtils.isBlank(loginDTO.getUsername())) {
            throw new CustomException("请输入用户名");
        }
        if (StringUtils.isBlank(loginDTO.getPassword())) {
            throw new CustomException("请输入密码");
        }
        if (StringUtils.isBlank(loginDTO.getCode())) {
            throw new CustomException("请输入验证码");
        }
        if (StringUtils.isBlank(loginDTO.getUuid())) {
            throw new CustomException("验证码已过期");
        }
        // 验证码
        String code = redisUtil.getAndDelete(RedisConst.Key.KAPTCHA_KEY + loginDTO.getUuid());
        // 不区分大小写
        if (StringUtils.isBlank(code) || !code.equalsIgnoreCase(loginDTO.getCode())) {
            throw new CustomException(LoginEnums.KAPTCHA_ERROR.getMsg());
        }
        // 验证账户
        LambdaQueryWrapper<SysUserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserInfo::getAccount, loginDTO.getUsername());
        SysUserInfo one = sysUserInfoService.getOne(queryWrapper, false);
        // 不存在
        if (null == one) {
            // 保存登录日志
            sysLoginLogService.saveSysLoginLog(loginDTO.getUsername(), 1, LoginEnums.ACCOUNT_NOT_EXIST.getMsg(), ServletUtils.getRequest());
            throw new CustomException(LoginEnums.ACCOUNT_NOT_EXIST.getMsg());
        }
        // 被冻结
        if (1 == one.getStatus()) {
            // 保存登录日志
            sysLoginLogService.saveSysLoginLog(loginDTO.getUsername(), 1, LoginEnums.ACCOUNT_SUSPEND.getMsg(), ServletUtils.getRequest());
            throw new CustomException(LoginEnums.ACCOUNT_SUSPEND.getMsg());
        }
        // 密码不正确
        if (!PasswordUtils.getPassword(one.getAccount(), loginDTO.getPassword()).equals(one.getPassword())) {
            // 保存登录日志
            sysLoginLogService.saveSysLoginLog(loginDTO.getUsername(), 1, LoginEnums.PASSWORD_ERROR.getMsg(), ServletUtils.getRequest());
            throw new CustomException(LoginEnums.PASSWORD_ERROR.getMsg());
        }
        // 执行登录
        StpUtil.login(one.getId(), loginDTO.getRememberMe());
        // 返回token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tokenName", StpUtil.getTokenName());
        jsonObject.put("tokenValue", StpUtil.getTokenValue());
        return RES.ok(jsonObject);
    }

    @Override
    public RES logout() {
        StpUtil.logout();
        return RES.ok();
    }

    @Override
    public RES getInfo() {
        RES res = RES.ok();
        res.put("user", this.getUserInfoFromSession());
        res.put("roles", stpInterface.getRoleList(StpUtil.getLoginId(), StpUtil.getLoginType()));
        res.put("permissions", stpInterface.getPermissionList(StpUtil.getLoginIdAsInt(), StpUtil.getLoginType()));
        return res;
    }

    @Override
    public RES getRouters() {
        // 从session中获取
        Object object = StpUtil.getTokenSession().get(SaTokenSessionConst.MENU_LIST);
        if (null != object) {
            return RES.ok(object);
        }
        List<SysMenuVO> routers = this.listRouters();
        List<RouterVO> list = this.buildMenus(routers);
        // 存入session中
        if (CollectionUtil.isNotEmpty(list)) {
            StpUtil.getTokenSession().set(SaTokenSessionConst.MENU_LIST, list);
        }
        return RES.ok(list);
    }

    @Override
    public SysUserInfo getUserInfoFromSession() {
        // 从session中获取
        SysUserInfo sysUserInfo = StpUtil.getTokenSession().getModel(SaTokenSessionConst.USER_INFO, SysUserInfo.class);
        if (null != sysUserInfo) {
            return sysUserInfo;
        }
        sysUserInfo = sysUserInfoService.getById(StpUtil.getLoginIdAsInt());
        // 存入session中
        if (null != sysUserInfo) {
            StpUtil.getTokenSession().set(SaTokenSessionConst.USER_INFO, sysUserInfo);
        }
        return sysUserInfo;
    }

    @Override
    public SysUserInfo getUserInfo(Integer id) {
        return sysUserInfoService.getById(id);
    }

    @Override
    public List<SysMenuVO> listRouters() {
        List<SysMenuVO> list = null;
        // 超管
        if (StpUtil.hasRole("administrator")) {
            list = sysPermissionInfoMapper.listSysMenuVOAll();
        } else {
            list = sysPermissionInfoMapper.listSysMenuVOAllByUserId(StpUtil.getLoginIdAsInt());
        }
        return getChildPerms(list, 0);
    }

    @Override
    public List<RouterVO> buildMenus(List<SysMenuVO> menus) {
        List<RouterVO> routers = new LinkedList<RouterVO>();
        for (SysMenuVO menu : menus) {
            RouterVO router = new RouterVO();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
            List<SysMenuVO> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && "M".equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVO> childrenList = new ArrayList<RouterVO>();
                RouterVO children = new RouterVO();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(null);
                router.setPath("/inner");
                List<RouterVO> childrenList = new ArrayList<RouterVO>();
                RouterVO children = new RouterVO();
                String routerPath = StringUtils.replaceEach(menu.getPath(), new String[]{CommonConstants.HTTP, CommonConstants.HTTPS}, new String[]{"", ""});
                children.setPath(routerPath);
                children.setComponent("InnerLink");
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuVO> getChildPerms(List<SysMenuVO> list, int parentId) {
        List<SysMenuVO> returnList = new ArrayList<>();
        for (Iterator<SysMenuVO> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuVO t = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenuVO> list, SysMenuVO t) {
        // 得到子节点列表
        List<SysMenuVO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuVO tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuVO> getChildList(List<SysMenuVO> list, SysMenuVO t) {
        List<SysMenuVO> tlist = new ArrayList<>();
        Iterator<SysMenuVO> it = list.iterator();
        while (it.hasNext()) {
            SysMenuVO n = it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuVO> list, SysMenuVO t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenuVO menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenuVO menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = StringUtils.replaceEach(routerPath, new String[]{CommonConstants.HTTP, CommonConstants.HTTPS}, new String[]{"", ""});
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && "M".equals(menu.getMenuType()) && "1".equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenuVO menu) {
        String component = "Layout";
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            component = "InnerLink";
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = "ParentView";
        }
        return component;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenuVO menu) {
        return menu.getParentId().intValue() != 0 && "M".equals(menu.getMenuType());
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(SysMenuVO menu) {
        return menu.getParentId().intValue() == 0 && "C".equals(menu.getMenuType()) && menu.getIsFrame().equals("1");
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenuVO menu) {
        return menu.getIsFrame().equals("1") && ishttp(menu.getPath());
    }

    /**
     * 是否为http(s)://开头
     *
     * @param link
     * @return
     */
    public boolean ishttp(String link) {
        return StringUtils.startsWithAny(link, CommonConstants.HTTP, CommonConstants.HTTPS);
    }
}
