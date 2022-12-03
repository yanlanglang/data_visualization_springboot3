package com.ns4a.dv.utils;

/**
 * @className: PrintInfoUtil
 * @author: Yan Lang
 * @date: 2022/12/1
 * @description:
 **/
public class PrintInfoUtil {

    /**
     * 打印对象的信息
     * @param obj
     */
    public static void printObj(Object obj){
        System.out.println("===============================================");
        System.out.println(obj);
        System.out.println("===============================================");
    }

    public static void printFlag(String str){
        System.out.println("------------------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------------------");
    }

    public static void printAboutRedisAbsent(String url){
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(url);
        System.out.println("此时redis中没值!");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

    }


    public static void printAboutRedisPresent(String url){
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("此时redis中有值!");
        System.out.println(url);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

    }

}
