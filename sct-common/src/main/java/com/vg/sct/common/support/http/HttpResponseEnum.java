package com.vg.sct.common.support.http;

public enum HttpResponseEnum {

    SUCCESS("success", "请求成功"),
    ERROR("error");

    HttpResponseEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    HttpResponseEnum(String code){
        this.code = code;
    }

    private String code;

    private String msg;

    public String getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

}
