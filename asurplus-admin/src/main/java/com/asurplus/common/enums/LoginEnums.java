package com.asurplus.common.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LoginEnums implements BaseEnums {

    LOGIN_SUCCESS(200, "登录成功"),
    LOGOUT_SUCCESS(200, "退出登录"),
    FORCE_OFFLINE(200, "强退下线"),
    REPLACE_OFFLINE(200, "被顶下线"),
    LIMIT_LOGIN(200, "账号被封禁"),
    UNSEAL_LOGIN(200, "账号已解封"),
    KAPTCHA_ERROR(201, "验证码不正确"),
    ACCOUNT_NOT_EXIST(202, "账户不存在"),
    PASSWORD_ERROR(203, "账户或密码不正确"),
    ACCOUNT_SUSPEND(204, "账户已被冻结"),
    ACCOUNT_LOCK(205, "密码连续输入错误超过5次，已锁定半小时"),
    UNKNOWN_ERROR(-1, "未知错误");

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
