package com.springboot.security.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.springboot.entity.SysUser;
import com.springboot.service.SysUserService;

@Component
public class UserDetailService implements UserDetailsService {

	@Autowired
	private SysUserService sysUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		SysUser user = sysUserService.getUserByUsername(username);
		if (StringUtils.isEmpty(username)) {
			throw new RuntimeException("用户名不能为空！");
		}
		if (user == null) {
			throw new RuntimeException("用户名不存在！");
		}

		// 模拟数据库权限查询
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return new MyUserDetails(user.getUsername(), user.getPassword(), authorities);
	}

}
