package com.vg.sct.user.repository;

import com.vg.sct.user.domain.model.SysUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUserModel, Integer> {

    List<SysUserModel> findByUserNameAndIsActiveTrue(String userName);
}
