package com.vg.sct.auth.repository;

import com.vg.sct.common.domain.model.sys.SysUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUserModel, Integer> {

    List<SysUserModel> findByUserName(String userName);
}
