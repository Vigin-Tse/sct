package com.vg.sct.common.utils;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: MD5算法工具类
 * @author: xieweij
 * @create: 2020-12-29 14:04
 **/
public class MD5Utils {


    /**
     * jdk原生方法
     * MD5加码 生成32位md5码
     * @param plainText
     * @return
     */
    public static String jencodeMd5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }

        //16是表示转换为16进制数
        String md5code = new BigInteger(1, secretBytes).toString(16);
        return md5code;
    }

    /**
     * spring提供方法
     * MD5加码 生成32位md5码
     * @param inStr
     * @return
     */
    public static String encodeMd5(String inStr) {
        String md5Str = DigestUtils.md5DigestAsHex(inStr.getBytes());
        return md5Str;
    }

    /**
     * 明文加密成md5对比
     * @param str       明文
     * @param encodeStr 密文
     * @return
     */
    public static boolean comparison(String str, String encodeStr){
        if(encodeStr.equals(encodeMd5(str))){
            return true;
        }
        return false;
    }

}
