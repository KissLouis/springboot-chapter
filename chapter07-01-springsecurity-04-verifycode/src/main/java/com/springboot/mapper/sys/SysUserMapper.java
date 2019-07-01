package com.springboot.mapper.sys;

import com.springboot.entity.sys.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Louis
 * @title: SysUserMapper
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/29 15:37
 */
@Mapper
public interface SysUserMapper {

    public SysUser getUserByUsername(@Param("userName") String userName);

    public void insert(SysUser user);

}
