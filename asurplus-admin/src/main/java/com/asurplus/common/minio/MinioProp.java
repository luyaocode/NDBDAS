package com.asurplus.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * minio 属性值
 *
 * @Author Lizhou
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProp {

    /**
     * 连接url
     */
    private String endpoint;
    /**
     * 用户名
     */
    private String accesskey;
    /**
     * 密码
     */
    private String secretKey;

}
