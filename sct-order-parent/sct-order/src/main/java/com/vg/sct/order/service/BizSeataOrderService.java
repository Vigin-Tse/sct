package com.vg.sct.order.service;

/**
 * 订单服务，分布式事务-seata测试
 * @author: xieweij
 * @time: 2021/7/14 15:58
 */
public interface BizSeataOrderService {

    /**
     * 模拟分布式系统下，不做事务处理情况
     */
    void createOrderWithNoTransaction();

    /**
     * 模拟分布式系统下，使用seata作事务管理
     */
    void createOrderWithSeata();
}
