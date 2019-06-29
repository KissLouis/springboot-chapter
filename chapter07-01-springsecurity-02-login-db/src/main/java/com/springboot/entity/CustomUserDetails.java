package com.springboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Louis
 * @title: CustomUserDetails
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/29 15:48
 */
@Data
@EqualsAndHashCode
public class CustomUserDetails extends User {

    private static final long serialVersionUID = -4461471539260584625L;
    private Long userId;
    private String nickname;
    private String language;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
