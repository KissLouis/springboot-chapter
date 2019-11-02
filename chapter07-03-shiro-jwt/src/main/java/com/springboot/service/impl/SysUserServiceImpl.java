package com.springboot.service.impl;

import com.springboot.dao.SysUserDao;
import com.springboot.entity.SysUser;
import com.springboot.jwt.util.JWTUtil;
import com.springboot.service.SysUserService;
import com.springboot.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SysUserDao sysUserDao;

    @Override
    @Transactional
    public void insert(SysUser user) {
        user.setSalt("salt");
        user.setUserId(user.getUsername());
        user.setPassword(MD5Util.MD5Pwd(user.getUsername(), user.getPassword()));
        sysUserDao.insert(user);
    }

    @Override
    public String login(String username, String password) {
        //进行密码
        String encryptPassword = MD5Util.MD5Pwd(username, password);
        if (encryptPassword.equals(sysUserDao.getPassword(username))) {
            return JWTUtil.sign(username, password);
        }
        return null;
    }

}
