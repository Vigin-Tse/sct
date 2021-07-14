package com.vg.sct.product.service.impl;

import com.vg.sct.common.support.exception.BusinessException;
import com.vg.sct.product.domain.model.ProSeataStorageModel;
import com.vg.sct.product.repository.ProSeataStorageRepository;
import com.vg.sct.product.service.ProSeataStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/7/14 15:30
 */
@Service
public class ProSeataStorageServiceImpl implements ProSeataStorageService {

    @Autowired
    private ProSeataStorageRepository storageRepository;

    @Override
    public void deduct(Integer productId, Integer count) {

        List<ProSeataStorageModel> storages = this.storageRepository.findByProductId(productId);
        if (CollectionUtils.isEmpty(storages)){
            throw new BusinessException("无可用库存");
        }

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        ProSeataStorageModel storage = storages.get(0);
        storage.setResidue(storage.getResidue() - count);
        this.storageRepository.save(storage);
    }
}
