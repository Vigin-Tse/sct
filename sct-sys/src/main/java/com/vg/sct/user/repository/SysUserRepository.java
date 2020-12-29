package com.vg.sct.user.repository;

import com.vg.sct.user.dao.model.SysUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUserModel, Integer> {

}
