package com.vg.sct.common.support.exception.base;

/**
 * @description: 自定义异常基础类
 * @author: xieweij
 * @create: 2020-12-29 11:17
 **/
public class BaseException extends RuntimeException {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseException(){
        super();
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(String code, String message){
        super(message);
        this.code = code;
    }
}
