package com.asurplus.common.utils;

import cn.dev33.satoken.secure.SaSecureUtil;


/**
 * 密码加密的处理工具类
 *
 * @Author Lizhou
 */
public class PasswordUtils {

    /**
     * 迭代次数
     */
    private static final int ITERATIONS = 6;

    private PasswordUtils() {
        throw new AssertionError();
    }

    /**
     * 字符串加密函数MD5实现
     *
     * @param account  用户名
     * @param password 密码
     * @return
     */
    public static String getPassword(String account, String password) {
        String str = "";
        for (int i = 0; i < ITERATIONS; i++) {
            str = SaSecureUtil.md5(account + password);
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(getPassword("admin", "123456"));
    }
}