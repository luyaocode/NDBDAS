package com.asurplus.common.satoken;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import com.asurplus.system.service.LoginService;
import com.asurplus.common.enums.LoginEnums;
import com.asurplus.common.utils.ServletUtils;
import com.asurplus.monitor.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义侦听器的实现
 */
@Component
public class SaTokenListenerImpl implements SaTokenListener {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 每次登录时触发
     */
    @Override
    public void doLogin(String loginType, Object loginId, SaLoginModel loginModel) {
        // 保存登录日志
        sysLoginLogService.saveSysLoginLog(loginService.getUserInfoFromSession().getAccount(), 0, LoginEnums.LOGIN_SUCCESS.getMsg(), ServletUtils.getRequest());
    }

    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        // 保存登录日志
        sysLoginLogService.saveSysLoginLog(loginService.getUserInfo(Integer.parseInt(String.valueOf(loginId))).getAccount(), 0, LoginEnums.LOGOUT_SUCCESS.getMsg(), ServletUtils.getRequest());
    }

    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        // 保存登录日志
        sysLoginLogService.saveSysLoginLog(loginService.getUserInfo(Integer.parseInt(String.valueOf(loginId))).getAccount(), 0, LoginEnums.FORCE_OFFLINE.getMsg(), ServletUtils.getRequest());
    }

    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        // 保存登录日志
        sysLoginLogService.saveSysLoginLog(loginService.getUserInfo(Integer.parseInt(String.valueOf(loginId))).getAccount(), 0, LoginEnums.REPLACE_OFFLINE.getMsg(), ServletUtils.getRequest());
    }

    /**
     * 每次被封禁时触发
     */
    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {
        // 保存登录日志
        sysLoginLogService.saveSysLoginLog(loginService.getUserInfo(Integer.parseInt(String.valueOf(loginId))).getAccount(), 0, LoginEnums.LIMIT_LOGIN.getMsg(), ServletUtils.getRequest());
    }

    /**
     * 每次被解封时触发
     */
    @Override
    public void doUntieDisable(String loginType, Object loginId) {
        // 保存登录日志
        sysLoginLogService.saveSysLoginLog(loginService.getUserInfo(Integer.parseInt(String.valueOf(loginId))).getAccount(), 0, LoginEnums.UNSEAL_LOGIN.getMsg(), ServletUtils.getRequest());
    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCreateSession(String id) {
        // ...
    }

    /**
     * 每次注销Session时触发
     */
    @Override
    public void doLogoutSession(String id) {
        // ...
    }

}
