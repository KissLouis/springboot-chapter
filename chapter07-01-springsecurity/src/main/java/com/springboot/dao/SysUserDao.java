package com.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.entity.SysUser;

/**
 * @author Louis
 * @title: SysUserMapper
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/29 15:37
 */
@Mapper
public interface SysUserDao {

    public SysUser getUserByUsername(@Param("userName") String userName);

    public void insert(SysUser user);

}
