package com.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.dao.SysUserDao;
import com.springboot.entity.SysUser;
import com.springboot.service.SysUserService;
import com.springboot.util.MD5Util;

/**
 * @author Louis
 * @title: SysUserServiceImpl
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/29 16:00
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	@Transactional
	public void insert(SysUser user) {
		user.setSalt("salt");
		user.setUserId(user.getUsername());
		user.setPassword(MD5Util.MD5Pwd(user.getUsername(), user.getPassword()));
		sysUserDao.insert(user);
	}

}
