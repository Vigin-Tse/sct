package com.vg.sct.common.support.http;

/**
 * @description: 响应统一返回类
 * @author: xieweij
 * @create: 2020-12-30 16:56
 **/
public class HttpResponseConvert {


    public static HttpResponse success(String msg){
        return new HttpResponse(HttpResponseEnum.SUCCESS.getCode(), msg);
    }

    public static <T> HttpResponse success(T data){
        return new HttpResponse(HttpResponseEnum.SUCCESS.getCode(), HttpResponseEnum.SUCCESS.getMsg(), data);
    }

    public static <T> HttpResponse success(String msg, T data){
        return new HttpResponse(HttpResponseEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> HttpResponse success(String code, String msg, T data){
        return new HttpResponse(code, msg, data);
    }

    public static HttpResponse failure(String msg){
        return new HttpResponse(HttpResponseEnum.ERROR.getCode(), msg);
    }

    public static HttpResponse failure(String code, String msg){
        return new HttpResponse(code, msg);
    }
}
