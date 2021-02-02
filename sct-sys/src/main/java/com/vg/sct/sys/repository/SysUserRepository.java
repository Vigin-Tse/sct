package com.vg.sct.sys.repository;

import com.vg.sct.sys.domain.po.SysUserPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUserPo, Integer> {

    List<SysUserPo> findByUserNameAndIsActiveTrue(String userName);

}
