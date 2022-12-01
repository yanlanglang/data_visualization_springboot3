package com.ns4a.dv.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @className: ResultJsonUtil
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description: 返回结果的工具类
 **/
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ResultJsonUtil {

    // 正确返回代码
    private static final String SUCCESS_CODE = "1";
    // 错误返回代码
    private static final String ERROR_CODE = "-1";

    //状态码
    private static final int CODE_ERROR = 404;

    private static final int CODE_SUCCESS = 0;

    // 状态代码
    private int code;
    // 信息
    private String msg;
    // 内容
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultJsonUtil() {}

    public ResultJsonUtil(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultJsonUtil(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultJsonUtils{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static ResultJsonUtil ok(Object data) {
        return ok("请求成功", data);
    }

    public static ResultJsonUtil ok(String msg, Object data) {
        return new ResultJsonUtil(CODE_SUCCESS, msg, data);
    }


    public static ResultJsonUtil error() {
        return error("请求失败");
    }

    public static ResultJsonUtil error(String msg) {
        return new ResultJsonUtil(CODE_ERROR, msg);
    }

}
