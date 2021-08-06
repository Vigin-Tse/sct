package com.vg.sct.common.support.http;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: xieweij
 * @time: 2021/8/5 11:44
 */
@Getter
@Setter
public class PageInfo {

    int pageNum;

    int pageSize;

    int pageTotal;

    int total;

    public PageInfo(){
        this.pageNum = 1;
        this.pageSize = 10;
        this.pageTotal = 0;
        this.total = 0;
    }
}
