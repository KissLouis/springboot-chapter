package com.springboot.dao;

import java.util.Set;

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

	public String getPassword(@Param("userName") String userName);

	public Set<String> getRoles(@Param("userName") String userName);
	
	public Set<String> getPermissions(@Param("userName") String userName);

}
