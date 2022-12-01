package com.ns4a.dv.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: DateTimeUtil
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description:
 **/
public class DateTimeUtil {

    public static void main(String[] args) throws ParseException {
        //获取Date类型的数据
        //参数不带短横线
        Date d1 = getDateWithStr("20110602", false);
        //参数带短横线
        Date d2 = getDateWithStr("2011-06-02", true);

        System.out.println(d1);
        System.out.println(d2);

        //获取String类型的伪日期
        //返回的结果不带短横线
        String s1 = getStrWithDate(new Date(), false);
        //返回的结果带短横线
        String s2 = getStrWithDate(new Date(), true);

        System.out.println(s1);
        System.out.println(s2);

    }


    /**
     * “yyyy-MM-dd” ---> yyyy-MM-dd
     * @param str 带短横线的字符串类型的伪日期
     * @param flag true：参数字符串带短横线；false：参数字符串不带短横线
     * @return
     * @throws ParseException
     */
    public static Date getDateWithStr(String str, boolean flag) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (flag){
            //带了短横线的字符串参数
            return sdf.parse(str);
        }else {
            //没带短横线的字符串参数（额外处理一下）
            String year = str.substring(0,4);
            String month = str.substring(4,6);
            String day = str.substring(6, 8);
            str = year+"-"+month+"-"+day;

            return sdf.parse(str);
        }
    }

    /**
     * Date date ---> "yyyy-MM-dd"
     * @param date Date类型的数据
     * @param flag true：返回带短横线的字符串；false：返回不带短横线的字符串
     * @return
     */
    public static String getStrWithDate(Date date, boolean flag){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (flag){
            //返回带短横线
            return sdf.format(date);
        }else {
            //返回不带短横线
            //(此时是yyyy-MM-dd)
            String formatStr = sdf.format(date);
            //切割字符串
            String year = formatStr.substring(0,4);
            String month = formatStr.substring(5,7);
            String day = formatStr.substring(8, 10);
            //拼接
            return year+month+day;
        }
    }


}
