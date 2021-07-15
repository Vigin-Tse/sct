package com.vg.sct.order.service;

/**
 * 订单服务，分布式事务-seata测试
 * @author: xieweij
 * @time: 2021/7/14 15:58
 */
public interface BizSeataOrderTempService {

    void localTransaction();
}
