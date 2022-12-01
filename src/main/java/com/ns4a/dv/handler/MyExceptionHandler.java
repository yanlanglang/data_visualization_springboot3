package com.ns4a.dv.handler;


import com.ns4a.dv.utils.ResultJsonUtil;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: MyExceptionHandler
 * @author: Yan Lang
 * @date: 2022/11/26
 * @description:
 **/
@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {

    /**
     * 统一异常处理(当前Controller适用)
     * @param ex exception
     * @return
     */
    @ExceptionHandler
    public ResultJsonUtil handleException(Exception ex){

        String errMessage = "";

        if (ex instanceof NumberFormatException){
            errMessage = "参数不是数字类型!";
        }else if (ex instanceof TypeMismatchException){
            errMessage = "参数不匹配!";
        } else if (ex instanceof NullPointerException){
            errMessage = "参数不能为空!";
        } else {
            errMessage = "其它类型错误！";
            ex.printStackTrace();
        }

        return ResultJsonUtil.error(errMessage);
    }


}
