package com.vg.sct.common.support.exception;

import com.vg.sct.common.support.exception.base.BaseException;

/**
 * @description: 权限认证异常类型
 * @author: xieweij
 * @create: 2020-12-29 11:14
 **/
public class AuthException extends BaseException {

    public AuthException(){
        super();
    }

    public AuthException(String message){
        super(message);
    }

    public AuthException(String code, String message){
        super(code, message);
    }
}
