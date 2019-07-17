package com.springboot.security.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	// 登录用户名
	@Setter
	private String username;
	// 登录密码
	@Setter
	private String password;

	@Setter
	private Collection<? extends GrantedAuthority> authorities;

	public MyUserDetails() {
		super();
	}

	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	/**
	 * 
	 * @描述: 账户是否未过期,过期无法验证
	 * @方法名: isAccountNonExpired
	 * @return
	 * @创建人：T-liul4
	 * @创建时间：2019年7月17日下午4:48:25
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 
	 * @描述: 指定用户是否解锁,锁定的用户无法进行身份验证
	 * @方法名: isAccountNonLocked
	 * @return
	 * @创建人：T-liul4
	 * @创建时间：2019年7月17日下午4:48:36
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @描述: 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
	 * @方法名: isCredentialsNonExpired
	 * @return
	 * @创建人：T-liul4
	 * @创建时间：2019年7月17日下午4:48:58
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @描述: 是否可用 ,禁用的用户不能身份验证
	 * @方法名: isEnabled
	 * @return
	 * @创建人：T-liul4
	 * @创建时间：2019年7月17日下午4:49:06
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		// 帐号是否被禁用(false：表示禁用/true：表示可用)
		return true;
	}

}
