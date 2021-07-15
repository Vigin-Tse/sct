package com.vg.sct.order.service.Impl;

import com.vg.sct.feign.account.api.AccSeataAccountFeignApi;
import com.vg.sct.feign.product.api.ProSeataStorageFeignApi;
import com.vg.sct.order.domain.model.BizSeataOrderModel;
import com.vg.sct.order.domain.model.BizSeataOrderTempModel;
import com.vg.sct.order.repository.BizSeataOrderRepository;
import com.vg.sct.order.repository.BizSeataOrderTempRepository;
import com.vg.sct.order.service.BizSeataOrderService;
import com.vg.sct.order.service.BizSeataOrderTempService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: xieweij
 * @time: 2021/7/14 16:08
 */
@Service
@Slf4j
public class BizSeataOrderServiceImpl implements BizSeataOrderService {

    @Resource
    private ProSeataStorageFeignApi storageApi;

    @Resource
    private AccSeataAccountFeignApi accApi;

    @Autowired
    private BizSeataOrderRepository orderRepository;

    @Autowired
    private BizSeataOrderTempService orderTempService;

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
        /**
         * 执行结果
         * 1.调用订单服务，创建订单成功。
         * 2.调用 库存服务 -> 超时 -> 重试一次 -> 导致库存扣减2次。
         * 3.库存服务调用超时时，消费者（订单）服务对其熔断，导致没有调用扣减余额服务。
         */
        this.createOrder();
    }

    @Override
    @GlobalTransactional(name = "order-service-createOrder", rollbackFor = Exception.class)
    public void createOrderWithSeata() {
        this.createOrder();
    }


    /**
     * 本地事务生效
     */
    @Transactional
    @Override
    public void localTransaction() {
        BizSeataOrderModel orderModel = new BizSeataOrderModel();
        orderModel.setUserId(1);
        orderModel.setProductId(1);
        orderModel.setCount(count);
        orderModel.setMoney(payMoney);
        this.orderRepository.save(orderModel);

        this.orderTempService.localTransaction();
    }

    private void createOrder(){
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

    private void doCreateOrder(Integer count, Double payMoney){
        BizSeataOrderModel orderModel = new BizSeataOrderModel();
        orderModel.setUserId(1);
        orderModel.setProductId(1);
        orderModel.setCount(count);
        orderModel.setMoney(payMoney);
        this.orderRepository.save(orderModel);
    }
}
