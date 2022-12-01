package com.ns4a.dv.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @className: Md5Util
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description: Md5加密
 **/
public class Md5Util {

    /**
     *
     * @param str 需要进行加密的字符串
     * @return 加密之后的字符串
     */
    public static String getCode(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
