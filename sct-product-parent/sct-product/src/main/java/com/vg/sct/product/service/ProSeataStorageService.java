package com.vg.sct.product.service;

/**
 * 仓储服务，分布式事务-seata测试
 * @author: xieweij
 * @time: 2021/7/14 15:27
 */
public interface ProSeataStorageService {

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 扣减（出库）数量
     */
    void deduct(Integer productId, Integer count);
}
