package com.vg.sct.account.service.impl;

import com.vg.sct.account.domain.model.AccSeataAccountModel;
import com.vg.sct.account.repository.AccSeataAccountRepository;
import com.vg.sct.common.support.exception.BusinessException;
import com.vg.sct.product.service.AccSeataAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/7/14 14:20
 */
@Service
public class AccSeataAccountServiceImpl implements AccSeataAccountService {

    @Autowired
    private AccSeataAccountRepository accSeataAccountRepository;

    @Override
    public void debit(Integer userId, Double payMoney) {
        List<AccSeataAccountModel> accounts = this.accSeataAccountRepository.findByUserId(userId);
        if (CollectionUtils.isEmpty(accounts)){
            throw new BusinessException("用户无可用余额");
        }


        AccSeataAccountModel acc = accounts.get(0);
        acc.setTotal(acc.getTotal() - payMoney);

        this.accSeataAccountRepository.save(acc);
    }
}
