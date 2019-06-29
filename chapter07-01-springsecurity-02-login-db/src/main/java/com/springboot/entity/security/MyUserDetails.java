package com.springboot.entity.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Louis
 * @title: MyUserDetails
 * @projectName springboot-chapter
 * @description: TODO 实现了UserDetails接口，只留必需的属性，也可添加自己需要的属性
 * @date 2019/6/27 21:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1892835995549646401L;

    private Long id;

    private String userId;

    //登录用户名
    private String username;
    //登录密码
    private String password;

    private Collection<? extends GrantedAuthority> Authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
