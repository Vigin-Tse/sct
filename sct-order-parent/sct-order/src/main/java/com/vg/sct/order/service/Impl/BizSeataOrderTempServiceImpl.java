package com.vg.sct.order.service.Impl;

import com.vg.sct.order.domain.model.BizSeataOrderTempModel;
import com.vg.sct.order.repository.BizSeataOrderTempRepository;
import com.vg.sct.order.service.BizSeataOrderTempService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xieweij
 * @time: 2021/7/14 16:08
 */
@Service
@Slf4j
public class BizSeataOrderTempServiceImpl implements BizSeataOrderTempService {

    @Autowired
    private BizSeataOrderTempRepository orderTempRepository;

    private Integer userId = 1;
    private Integer productId = 1;
    private Integer count = 10;
    private Double payMoney = 100d;


    @Override
    public void localTransaction() {

        BizSeataOrderTempModel orderTempModel = new BizSeataOrderTempModel();
        orderTempModel.setUserId(1);
        orderTempModel.setProductId(1);
        orderTempModel.setCount(count);
        orderTempModel.setMoney(payMoney);
        this.orderTempRepository.save(orderTempModel);
        int i = 1/0;

    }

}
