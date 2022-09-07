package com.asurplus.common.redis;

/**
 * redis常量类
 *
 * @Author Lizhou
 */
public final class RedisConst {

    /**
     * Redis 键
     */
    public static final class Key {
        /**
         * 系统信息缓存
         */
        public static final String SYS_CONFIGURE = ":sys:configure";

        /**
         * 系统参数配置
         */
        public static final String SYS_PARAM_CONF = ":sys:paramConf";

        /**
         * 服务监控信息
         */
        public static final String SYS_SERVER_INFO = ":sys:server_info";

        /**
         * 微信获取微信access_token
         */
        public static final String WEIXIN_ACCESS_TOKEN = ":weixin:access_token";

        /**
         * jwt
         */
        public static final String API_TOKEN = ":api_token:";

        /**
         * kaptcha验证码
         */
        public static final String KAPTCHA_KEY = ":kaptcha:";
    }

    /**
     * Redis 通道
     */
    public static final class Channel {

    }
}
