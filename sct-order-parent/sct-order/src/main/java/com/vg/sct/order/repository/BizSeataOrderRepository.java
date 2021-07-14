package com.vg.sct.order.repository;

import com.vg.sct.order.domain.model.BizSeataOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xieweij
 * @time: 2021/7/14 13:49
 */
public interface BizSeataOrderRepository extends JpaRepository<BizSeataOrderModel, Integer> {
}
