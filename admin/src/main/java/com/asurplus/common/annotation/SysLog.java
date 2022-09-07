package com.asurplus.common.annotation;

import com.asurplus.common.enums.BusinessType;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author Lizhou
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType type() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean saveParam() default true;
}
