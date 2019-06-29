package com.springboot.config.security;

import com.springboot.entity.security.MyUserDetails;
import com.springboot.entity.sys.SysUser;
import com.springboot.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Louis
 * @title: MyCustomUserService
 * @projectName springboot-chapter
 * @description: TODO 登录专用类,用户登陆时，通过这里查询数据库 自定义类，实现了UserDetailsService接口，用户登录时调用的第一类
 * @date 2019/6/27 21:10
 */
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * @param [username]
     * @return org.springframework.security.core.userdetails.UserDetails
     * @description: //TODO 登陆验证时，通过username获取用户的所有权限信息 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     * @author Louis
     * @date 2019/6/27 21:37
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //在这里可以自己调用数据库，对username进行查询，看看在数据库中是否存在
        SysUser user = sysUserService.getUserByUsername(username);

        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("用户名不能为空！");
        }
        if (user == null) {
            throw new RuntimeException("用户名不存在！");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new MyUserDetails(user.getId(), user.getUserId(), user.getUserName(), user.getPassword(), authorities);
    }
}
