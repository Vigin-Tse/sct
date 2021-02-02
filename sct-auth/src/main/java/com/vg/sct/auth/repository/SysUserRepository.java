package com.vg.sct.auth.repository;

import com.vg.sct.common.domain.po.sys.SysUserPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUserPo, Integer> {

    List<SysUserPo> findByUserName(String userName);
}
