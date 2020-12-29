package com.vg.sct.common.exception;

import com.vg.sct.common.exception.base.BaseException;

/**
 * @description: 业务异常
 * @author: xieweij
 * @create: 2020-12-29 11:15
 **/
public class BusinessException extends BaseException {

    public BusinessException(){
        super();
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String code, String message){
        super(code, message);
    }
}
