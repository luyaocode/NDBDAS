package com.asurplus.common.ip;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取地址类
 *
 * @author lizhou
 */
@Slf4j
@Component
public class AddressUtils {

    @Autowired
    private Ip2regionSearcher ip2regionSearcher;

    /**
     * 未知地址
     */
    public static final String UNKNOWN = "未知地理位置";

    /**
     * 查询ip地址
     *
     * @param ip
     * @return
     */
    public String getAddress(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP，无法获取位置";
        }
        try {
            // 获取地址
            return ip2regionSearcher.getAddress(ip);
        } catch (Exception e) {
            log.error("获取地理位置异常 {}", ip);
        }
        return UNKNOWN;
    }
}
