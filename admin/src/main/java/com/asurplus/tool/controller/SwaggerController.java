package com.asurplus.tool.controller;

import com.asurplus.common.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * swagger 接口
 *
 * @author lizhou
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController {

    @GetMapping
    public String init() {
        return StringUtils.format("redirect:{}", "/doc.html");
    }
}
