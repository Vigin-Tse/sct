package com.vg.sct.common.support.exception.handler;

import com.vg.sct.common.support.exception.AuthException;
import com.vg.sct.common.support.exception.BusinessException;
import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @description: 全局异常处理器
 * @author: xieweij
 * @create: 2020-12-31 11:13
 **/
//@ControllerAdvice 和 @RestControllerAdvice都是对Controller进行增强的，可以全局捕获spring mvc抛的异常。(配合@ExceptionHandler使用)
//有这个注解的类中的方法的某些注解会应用到所有的Controller里，其中就包括@ExceptionHandler注解。
//@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //@ExceptionHandler必须要求该方法必须要和出现问题的控制器在一个类中才能生效.(通过RestControllerAdvice注解可以应用到所有controller中)
    @ExceptionHandler(AuthException.class)
    public HttpResponse authExceptionHandler(AuthException e){
        log.error("异常信息：", e);
        return HttpResponseConvert.failure(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public HttpResponse businessExceptionHandler(BusinessException e){
        log.error("异常信息：", e);
        return HttpResponseConvert.failure(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public HttpResponse exceptionHandler(Exception e){
        log.error("异常信息：", e);
        return HttpResponseConvert.failure(e.getMessage());
    }
}
