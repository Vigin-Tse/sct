package com.vg.sct.user.service.impl;

import com.vg.sct.user.dao.mapper.SysUserMapper;
import com.vg.sct.user.dao.model.SysUserModel;
import com.vg.sct.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:49
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUserModel getOne(Integer id) {
        return this.userMapper.getOne(id);
    }
}
