package com.vg.sct.product.service;

/**
 * 账户服务，分布式事务-seata测试
 * @author: xieweij
 * @time: 2021/7/14 14:06
 */
public interface AccSeataAccountService {

    /**
     * 从账号扣减金额
     */
    void debit(Integer userId, Double payMoney);

}
