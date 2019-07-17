package com.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.dao.SysUserDao;
import com.springboot.entity.SysUser;
import com.springboot.service.SysUserService;

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
	public SysUser getUserByUsername(String userName) {
		return sysUserDao.getUserByUsername(userName);
	}

	@Override
	@Transactional
	public void insert(SysUser user) {
		if (exist(user.getUsername())) {
			throw new RuntimeException("用户名已存在！");
		}
		user.setSalt("salt");
		user.setUserId(user.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		sysUserDao.insert(user);
	}

	private boolean exist(String username) {
		SysUser user = sysUserDao.getUserByUsername(username);
		return (user != null);
	}
}
