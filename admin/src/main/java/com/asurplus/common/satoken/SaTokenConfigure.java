package com.asurplus.common.satoken;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Sa-Token代码方式进行配置
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器
        registry.addInterceptor(new SaRouteInterceptor((req, resp, handler) -> {
            // 白名单
            List<String> ignoreUrls = new ArrayList<>();
            // 验证码
            ignoreUrls.add("/kaptcha-image");
            // 登录
            ignoreUrls.add("/login");
            // 登出
            ignoreUrls.add("/logout");
            // api接口
            ignoreUrls.add("/api/**");
            // api文档
            ignoreUrls.add("/swagger-resources/**");
            ignoreUrls.add("/webjars/**");
            ignoreUrls.add("/v2/**");
            ignoreUrls.add("/swagger-ui.html/**");
            ignoreUrls.add("/doc.html/**");
            ignoreUrls.add("/error");
            ignoreUrls.add("/favicon.ico");
            // 静态资源
            ignoreUrls.add("/**/*.html");
            ignoreUrls.add("/**/*.css");
            ignoreUrls.add("/**/*.js");
            // 除白名单路径外均需要登录认证
            SaRouter.match("/**").notMatch(ignoreUrls).check(r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaAnnotationInterceptor())
                // 需要拦截的路径
                .addPathPatterns("/**");
    }
}
