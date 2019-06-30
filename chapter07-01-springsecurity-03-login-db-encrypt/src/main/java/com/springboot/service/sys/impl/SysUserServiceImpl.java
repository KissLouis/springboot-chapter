package com.springboot.service.sys.impl;

import com.springboot.entity.sys.SysUser;
import com.springboot.mapper.sys.SysUserMapper;
import com.springboot.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUsername(String userName) {
        return sysUserMapper.getUserByUsername(userName);
    }

    @Override
    @Transactional
    public void insert(SysUser user) {
        if (exist(user.getUserName())) {
            throw new RuntimeException("用户名已存在！");
        }
        user.setSalt("salt：" + user.getPassword());
        user.setUserId(user.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        sysUserMapper.insert(user);
    }

    private boolean exist(String username) {
        SysUser user = sysUserMapper.getUserByUsername(username);
        return (user != null);
    }
}
