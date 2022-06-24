package com.vg.sct.product.service.impl;

import com.vg.sct.common.support.exception.BusinessException;
import com.vg.sct.product.domain.model.ProSeataStorageModel;
import com.vg.sct.product.repository.ProSeataStorageRepository;
import com.vg.sct.product.service.ProSeataStorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/7/14 15:30
 */
@Service
@Slf4j
public class ProSeataStorageServiceImpl implements ProSeataStorageService {

    @Autowired
    private ProSeataStorageRepository storageRepository;

    @GlobalTransactional(name = "product-service-deduct", rollbackFor = Exception.class)
    @Override
    public void deduct(Integer productId, Integer count) {
        log.info("进入库存服务");

        List<ProSeataStorageModel> storages = this.storageRepository.findByProductId(productId);
        if (CollectionUtils.isEmpty(storages)){
            throw new BusinessException("无可用库存");
        }

        ProSeataStorageModel storage = storages.get(0);
        storage.setResidue(storage.getResidue() - count);
        this.storageRepository.save(storage);

        //模拟库存扣减发生异常
        int i = 1/0;
        log.info("退出库存服务");
    }
}
