package com.vg.sct.common.support.exception;

import com.vg.sct.common.support.exception.base.BaseException;

public class UnCallableException extends BaseException {

    public UnCallableException(){
        super();
    }

    public UnCallableException(String message){
        super(message);
    }

    public UnCallableException(String code, String message){
        super(code, message);
    }

}
