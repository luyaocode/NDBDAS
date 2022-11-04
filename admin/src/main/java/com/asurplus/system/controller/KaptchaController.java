package com.asurplus.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.asurplus.common.exception.CustomException;
import com.asurplus.common.redis.RedisConst;
import com.asurplus.common.redis.RedisUtil;
import com.asurplus.common.utils.Base64;
import com.asurplus.common.utils.RES;
import com.asurplus.common.utils.RandomUtils;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

/**
 * kaptcha调用
 *
 * @Author Lizhou
 **/
@Api(tags = "获取验证码")
@Slf4j
@RestController
public class KaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "获取验证码")
    @GetMapping("kaptcha-image")
    public RES getKaptchaImage() {
        // 类型
        int category = 1;
        // 验证码
        String capText = null;
        BufferedImage image = null;
        // 字符型
        if (0 == category) {
            capText = RandomUtils.number(4);
            image = captchaProducer.createImage(capText);
        }
        // 计算型
        else if (1 == category) {
            capText = captchaProducerMath.createText();
            String capStr = capText.substring(0, capText.lastIndexOf("@"));
            capText = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        }
        // 使用uuid作为key
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 存入redis
        redisUtil.set(RedisConst.Key.KAPTCHA_KEY + uuid, capText, 5 * 60);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            throw new CustomException(e.getMessage());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", uuid);
        jsonObject.put("img", Base64.encode(os.toByteArray()));
        return RES.ok(jsonObject);
    }
}
