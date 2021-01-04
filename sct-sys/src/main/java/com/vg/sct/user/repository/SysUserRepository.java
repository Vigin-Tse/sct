package com.vg.sct.user.repository;

import com.vg.sct.user.domain.po.SysUserPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUserPo, Integer> {

    List<SysUserPo> findByUserNameAndIsActiveTrue(String userName);
}
