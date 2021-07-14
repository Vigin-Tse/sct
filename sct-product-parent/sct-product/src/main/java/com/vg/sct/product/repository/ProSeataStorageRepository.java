package com.vg.sct.product.repository;

import com.vg.sct.product.domain.model.ProSeataStorageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/7/14 13:49
 */
public interface ProSeataStorageRepository extends JpaRepository<ProSeataStorageModel, Integer> {

    List<ProSeataStorageModel> findByProductId(Integer productId);
}
