package com.asurplus.common.config;

import com.asurplus.common.consts.SystemConst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * swagger2 配置
 *
 * @Author Lizhou
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    /**
     * 是否开启swagger
     */
    @Value("${knife4j.enabled}")
    private boolean enabled;

    @Bean("appApi")
    public Docket appApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 是否开启
                .enable(enabled)
                // 分组名称
                .groupName("APP端API文档")
                .apiInfo(apiInfo())
                .select()
                // 扫描包位置，扫描所有带有此注解的方法
                .apis(RequestHandlerSelectors.basePackage("com.asurplus.api"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean("adminApi")
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 是否开启
                .enable(enabled)
                // 分组名称
                .groupName("管理端API文档")
                .apiInfo(apiInfo())
                .select()
                // 扫描包位置，扫描所有带有此注解的方法
                .apis(RequestHandlerSelectors.basePackage("com.asurplus.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title(SystemConst.SYSTEM_ITEM_NAME.substring(0, 1).toUpperCase() + SystemConst.SYSTEM_ITEM_NAME.substring(1) + "管理后台服务API接口文档")
                // 简介
                .description("使用 knife4j 搭建的管理后台服务API在线接口文档")
                // 服务条款url
                .termsOfServiceUrl("无")
                // 版本
                .version("1.0.0")
                // 作者
                .contact(new Contact("Administrators", "无", "无"))
                // 构建
                .build();
    }
}
