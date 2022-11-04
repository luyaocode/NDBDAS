package com.asurplus.system.vo;

import lombok.Data;

@Data
public class LoginDTO {

    /**
     * 账户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码uuid
     */
    private String uuid;

    /**
     * 验证码
     */
    private String code;

    /**
     * 记住登录
     */
    private Boolean rememberMe = true;
}
