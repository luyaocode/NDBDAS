package com.asurplus.common.redis;

import com.asurplus.common.consts.SystemConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.Charset;

/**
 * 为redis key 统一加上前缀
 */
@Slf4j
public class RedisKeySerializer implements RedisSerializer<String> {

    /**
     * 编码格式
     */
    private final Charset charset;

    /**
     * 前缀
     */
    private final String PREFIX_KEY = SystemConst.SYSTEM_ITEM_NAME;

    public RedisKeySerializer() {
        this(Charset.forName("UTF8"));
    }

    public RedisKeySerializer(Charset charset) {
        this.charset = charset;
    }

    @Override
    public String deserialize(byte[] bytes) {
        String saveKey = new String(bytes, charset);
        int indexOf = saveKey.indexOf(PREFIX_KEY + ":");
        if (indexOf > 0) {
            log.error("key缺少前缀");
        } else {
            saveKey = saveKey.substring(indexOf);
        }
        return (saveKey.getBytes() == null ? null : saveKey);
    }

    @Override
    public byte[] serialize(String string) {
        String key = PREFIX_KEY + string;
        return (key == null ? null : key.getBytes(charset));
    }
}