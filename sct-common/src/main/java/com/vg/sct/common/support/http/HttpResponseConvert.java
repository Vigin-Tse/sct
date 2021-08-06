package com.vg.sct.common.support.http;

/**
 * @description: 响应统一返回类
 * @author: xieweij
 * @create: 2020-12-30 16:56
 **/
public class HttpResponseConvert {
    public static <T> HttpResponse success(){
        return success(HttpResponseEnum.SUCCESS.getCode(), HttpResponseEnum.SUCCESS.getMsg(), null, null);
    }

    public static <T> HttpResponse success(T data){
        return success(HttpResponseEnum.SUCCESS.getCode(), HttpResponseEnum.SUCCESS.getMsg(), data, null);
    }

    public static <T> HttpResponse success(T data, PageInfo pageInfo){
        return success(HttpResponseEnum.SUCCESS.getCode(), HttpResponseEnum.SUCCESS.getMsg(), data, pageInfo);
    }


    public static <T> HttpResponse success(String msg, T data){
        return success(HttpResponseEnum.SUCCESS.getCode(), msg, data, null);
    }

    public static <T> HttpResponse success(String msg, T data, PageInfo pageInfo){
        return success(HttpResponseEnum.SUCCESS.getCode(), msg, data, pageInfo);
    }

    public static <T> HttpResponse success(String code, String msg, T data){
        return success(code, msg, data, null);
    }

    public static <T> HttpResponse success(String code, String msg, T data, PageInfo pageInfo){
        return new HttpResponse(code, msg, data, pageInfo);
    }

    public static HttpResponse failure(String msg){
        return failure(HttpResponseEnum.ERROR.getCode(), msg);
    }

    public static HttpResponse failure(String code, String msg){
        return new HttpResponse(code, msg);
    }
}
