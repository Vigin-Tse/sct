package com.vg.sct.common.http;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HttpResponse<T> implements Serializable {

    private static final long serialVersionUID = -284719732991678911L;

    /**
     * 返回标识：
     *        success
     *        error
     */
    private String code;

    /**
     * 返回信息说明
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public HttpResponse(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public HttpResponse(String code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
