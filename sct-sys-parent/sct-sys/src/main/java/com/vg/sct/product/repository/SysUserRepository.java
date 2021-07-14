package com.vg.sct.product.repository;


import com.vg.sct.common.domain.model.sys.SysUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUserModel, Integer> {

    List<SysUserModel> findByUserNameAndIsActiveTrue(String userName);

}
