package com.asurplus.common.exception;

/**
 * 自定义异常
 *
 * @author lizhou
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code = 500;

    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}