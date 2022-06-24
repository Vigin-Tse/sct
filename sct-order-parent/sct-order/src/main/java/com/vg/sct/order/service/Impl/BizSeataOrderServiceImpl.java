package com.vg.sct.order.service.Impl;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.feign.account.api.AccSeataAccountFeignApi;
import com.vg.sct.feign.product.api.ProSeataStorageFeignApi;
import com.vg.sct.order.domain.model.BizSeataOrderModel;
import com.vg.sct.order.repository.BizSeataOrderRepository;
import com.vg.sct.order.service.BizSeataOrderService;
import com.vg.sct.order.service.BizSeataOrderTempService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;

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

    private DataSource dataSource;

    private DataSourceTransactionManager tx;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setTx(DataSourceTransactionManager tx) {
        this.tx = tx;
    }

    private Integer userId = 1;
    private Integer productId = 1;
    private Integer count = 10;
    private Double payMoney = 100d;

    /**
     * 场景：
     * 1.创建订单
     * 2.扣减余额
     * 3.商品出库（库存扣减）扣减余额
     */

    @Override
    public void createOrderWithNoTransaction() {
        /**
         * 执行结果
         * 1.调用订单服务，创建订单成功。
         * 2.调用 账号服务扣减余额
         * 3.库存服务扣减失败
         * 4.结果 下了单、扣了钱、没出库
         */
        this.createOrder();
    }

    /**
     * 子服务报错 要能被 @GlobalTransactional 成功捕捉，
     * 子服务不能自己处理异常（如：try/catch、全局异常捕捉、熔断\降级处理等），否则不能正常回滚
     */
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

        HttpResponse resp;

        //扣减余额
        log.info("扣减余额开始");
        resp = accApi.debit(userId, payMoney);
        log.info("结果：" + resp.getMsg());
        log.info("扣减余额结束");

        //扣减库存
        log.info("扣减库存开始");
        resp = storageApi.deduct(productId, count);
        log.info("结果：" + resp.getMsg());
        log.info("扣减库存结束");

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
