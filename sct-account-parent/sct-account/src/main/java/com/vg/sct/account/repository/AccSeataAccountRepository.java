package com.vg.sct.account.repository;

import com.vg.sct.account.domain.model.AccSeataAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/7/14 13:42
 */
public interface AccSeataAccountRepository extends JpaRepository<AccSeataAccountModel, Integer> {

    List<AccSeataAccountModel> findByUserId(Integer userId);
}
