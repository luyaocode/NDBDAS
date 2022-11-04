package com.asurplus.common.config;

import com.asurplus.common.exception.CustomException;
import com.asurplus.common.utils.RES;
import com.asurplus.common.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理
 *
 * @Author Lizhou
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public RES customException(CustomException e) {
        return RES.no(e.getCode(), e.getMessage());
    }

    /**
     * 404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public RES handlerNoFoundException(Exception e) {
        return RES.no(404, "路径不存在，请检查路径是否正确");
    }

    /**
     * 字段验证异常
     */
    @ExceptionHandler(BindException.class)
    public RES bindException(BindException e) {
        return RES.no(e.getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RES methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return RES.no(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
