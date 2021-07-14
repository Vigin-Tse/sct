package com.vg.sct.order.service.Impl;

import com.vg.sct.feign.account.api.AccSeataAccountFeignApi;
import com.vg.sct.feign.product.api.ProSeataStorageFeignApi;
import com.vg.sct.order.domain.model.BizSeataOrderModel;
import com.vg.sct.order.repository.BizSeataOrderRepository;
import com.vg.sct.order.service.BizSeataOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xieweij
 * @time: 2021/7/14 16:08
 */
@Service
@Slf4j
public class BizSeataOrderServiceImpl implements BizSeataOrderService {

    @Autowired
    private ProSeataStorageFeignApi storageApi;

    @Autowired
    private AccSeataAccountFeignApi accApi;

    @Autowired
    private BizSeataOrderRepository orderRepository;

    private Integer userId = 1;
    private Integer productId = 1;
    private Integer count = 10;
    private Double payMoney = 100d;

    /**
     * 场景：
     * 1.创建订单
     * 2.商品出库（库存扣减）
     * 3.扣减余额
     */
    @Override
    public void createOrderWithNoTransaction() {
        //创建订单
        log.info("下单开始");
        this.doCreateOrder(count, payMoney);
        log.info("下单结束");

        //扣减库存
        log.info("扣减库存开始");
        storageApi.deduct(productId, count);
        log.info("扣减库存结束");

        //扣减余额
        log.info("扣减余额开始");
        accApi.debit(userId, payMoney);
        log.info("扣减余额结束");
    }

    @Override
    public void createOrderWithSeata() {

    }


    private void doCreateOrder(Integer count, Double payMoney){
        BizSeataOrderModel orderModel = new BizSeataOrderModel();
        orderModel.setUserId(1);
        orderModel.setProductId(1);
        orderModel.setCount(count);
        orderModel.setMoney(payMoney);
        this.orderRepository.save(orderModel);
    }
}
