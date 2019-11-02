package com.springboot.jwt;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {
	
	private String token;

	public JWTToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return token;
	}

}
