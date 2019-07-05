package com.springboot.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Louis
 * @title: SmsCodeAuthenticationToken
 * @projectName springboot-chapter
 * @description: TODO 短信登录 AuthenticationToken，模仿 UsernamePasswordAuthenticationToken 实现
 * @date 2019/7/4 21:25
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = -8162480231959294642L;

    /**
     * @description: //TODO 在 UsernamePasswordAuthenticationToken 中该字段代表登录的用户名，在这里代表登录的手机号码
     * @author Louis
     * @date 2019/7/4 21:26
     * @param
     * @return
     */
    private final Object principal;

    /**
     * @param [authorities]
     * @return
     * @description: //TODO 构建一个没有鉴权的 SmsCodeAuthenticationToken
     * @author Louis
     * @date 2019/7/4 21:26
     */
    public SmsCodeAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    /**
     * @param [authorities]
     * @return
     * @description: //TODO 构建拥有鉴权的 SmsCodeAuthenticationToken
     * @author Louis
     * @date 2019/7/4 21:27
     */
    public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

}
