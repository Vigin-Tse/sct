package com.vg.sct.auth.config.handler;

import com.vg.sct.common.exception.handler.GlobalExceptionHandler;
import com.vg.sct.common.http.HttpResponse;
import com.vg.sct.common.http.HttpResponseConvert;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 异常处理
 * @author: xieweij
 * @create: 2020-12-31 11:41
 **/
@RestControllerAdvice
public class CustomExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(InvalidGrantException.class)
    public HttpResponse invalidGrantExceptionHandler(InvalidGrantException e){
        return HttpResponseConvert.failure(e.getMessage());
    }
}
