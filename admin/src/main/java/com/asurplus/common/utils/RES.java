package com.asurplus.common.utils;

import com.asurplus.common.enums.BaseEnums;
import com.asurplus.common.enums.StatusEnums;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;

/**
 * 接口统一返回数据
 * 实际上是一个HashMap
 * 键（左边的相当于宏，等同于右边的字符串）
 * CODE_TAG = "code"， 状态码
 * MSG_TAG = "msg"，提示信息
 * DATA_TAG = "data"，从数据库查到的数据
 * 如果查到的是一个List对象，那么set方法就是将该对象put到map里面。打印List直接输出属性信息
 *
 *
 * @Author Lizhou
 */
public class RES extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码")
    public static final String CODE_TAG = "code";

    @ApiModelProperty(value = "提示信息")
    public static final String MSG_TAG = "msg";

    @ApiModelProperty(value = "返回数据")
    public static final String DATA_TAG = "data";

    /**
     * 无参构造方法
     */
    public RES() {

    }

    public RES(Integer code) {
        super.put(CODE_TAG, code);
    }

    public RES(Integer code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public RES(Integer code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (null != data) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 请求成功返回
     *
     * @return
     */
    public static RES ok() {
        return new RES(StatusEnums.OK.getCode(), StatusEnums.OK.getMsg(), null);
    }

    public static RES ok(Integer code) {
        return new RES(code, StatusEnums.OK.getMsg(), null);
    }

    public static RES ok(String msg) {
        return new RES(StatusEnums.OK.getCode(), msg, null);
    }

    public static RES ok(Object data) {
        return new RES(StatusEnums.OK.getCode(), StatusEnums.OK.getMsg(), data);
    }

    public static RES ok(Integer code, String msg) {
        return new RES(code, msg, null);
    }

    public static RES ok(Integer code, Object data) {
        return new RES(code, StatusEnums.OK.getMsg(), data);
    }

    public static RES ok(String msg, Object data) {
        return new RES(StatusEnums.OK.getCode(), msg, data);
    }

    public static RES ok(Integer code, String msg, Object data) {
        return new RES(code, msg, data);
    }

    public static RES ok(BaseEnums baseEnums) {
        return new RES(baseEnums.getCode(), baseEnums.getMsg(), null);
    }

    /**
     * 请求失败返回
     */
    public static RES no() {
        return new RES(StatusEnums.ERROR.getCode(), StatusEnums.ERROR.getMsg(), null);
    }

    public static RES no(Integer code) {
        return new RES(code, StatusEnums.ERROR.getMsg(), null);
    }

    public static RES no(String msg) {
        return new RES(StatusEnums.ERROR.getCode(), msg, null);
    }

    public static RES no(Object data) {
        return new RES(StatusEnums.ERROR.getCode(), StatusEnums.ERROR.getMsg(), data);
    }

    public static RES no(Integer code, String msg) {
        return new RES(code, msg, null);
    }

    public static RES no(Integer code, Object data) {
        return new RES(code, StatusEnums.ERROR.getMsg(), data);
    }

    public static RES no(String msg, Object data) {
        return new RES(StatusEnums.ERROR.getCode(), msg, data);
    }

    public static RES no(Integer code, String msg, Object data) {
        return new RES(code, msg, data);
    }

    public static RES no(BaseEnums baseEnums) {
        return new RES(baseEnums.getCode(), baseEnums.getMsg(), null);
    }

    /**
     * 获取code
     */
    public Integer getCode() {
        return Integer.parseInt(String.valueOf(this.get(CODE_TAG)));
    }

    /**
     * 获取msg
     */
    public String getMsg() {
        return String.valueOf(this.get(MSG_TAG));
    }

    /**
     * 获取data
     */
    public Object getData() {
        return this.get(DATA_TAG);
    }

    /**
     * 判断请求成功还是失败
     */
    public boolean isSuccess() {
        if (String.valueOf(StatusEnums.OK.getCode()).equals(String.valueOf(this.get(CODE_TAG)))) {
            return true;
        }
        return false;
    }
}
