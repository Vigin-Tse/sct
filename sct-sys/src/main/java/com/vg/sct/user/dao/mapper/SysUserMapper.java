package com.vg.sct.user.dao.mapper;

import com.vg.sct.user.dao.model.SysUserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:25
 */
@Mapper
public interface SysUserMapper {

    @Insert("INSERT INTO sys_user(user_name, nick_name, password) VALUES(#{userName}, #{nickName}, #{password})")
    void insert(SysUserModel user);

    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUserModel getOne(Integer id);
}
