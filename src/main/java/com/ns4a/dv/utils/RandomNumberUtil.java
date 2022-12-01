package com.ns4a.dv.utils;

/**
 * @className: RandomNumberUtil
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description: 随机数字工具包
 **/
public class RandomNumberUtil {

    //默认长度
    private final static int DEFAULT_LENGTH = 11;

    /**
     * 获取指定长度的字符串
     * @param length 0即默认11位
     * @return
     */
    public static String getRandomNumber(int length){
        int rs = 0;

        if (length==0){
            rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, DEFAULT_LENGTH - 1));
        }else {
            rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, length - 1));
        }

        return String.valueOf(rs);
    }

}
