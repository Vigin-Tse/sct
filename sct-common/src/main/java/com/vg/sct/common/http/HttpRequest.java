package com.vg.sct.common.http;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: http请求统一封装
 * @author: xieweij
 * @create: 2020-10-09 16:33
 **/
@Setter
@Getter
public class HttpRequest<T> {

    private T body;
}
