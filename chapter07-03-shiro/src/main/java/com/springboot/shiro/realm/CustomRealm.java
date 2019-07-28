package com.springboot.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.dao.SysUserDao;
import com.springboot.util.MD5Util;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @类描述： 自定义 realm 类
 * 
 * @项目名称：chapter07-03-shiro @包名： com.springboot.shiro.realm
 * @类名称：CustomRealm
 * @创建人：Louis
 * @创建时间：2019年7月23日上午9:56:43
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private SysUserDao sysUserDao;

	/**
	 * 
	 * @描述: 获取身份验证信息
	 * @方法名: doGetAuthenticationInfo
	 * @param authenticationToken
	 *            用户身份信息 token
	 * @return 返回封装了用户信息的 AuthenticationInfo 实例
	 * @throws AuthenticationException
	 * @创建人：T-liul4
	 * @创建时间：2019年7月23日上午9:58:58
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		log.info("身份认证方法：doGetAuthenticationInfo");

		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 输入后的帐号及加密的密码
		String inputUserName = token.getUsername();
		String inputPassword = MD5Util.MD5Pwd(inputUserName, new String((char[]) token.getCredentials()));
		// 根据用户名从数据库获取密码
		String password = sysUserDao.getPassword(inputUserName);
		if (password == null) {
			throw new AccountException("用户名不正确");
		} else if (!password.equals(inputPassword)) {
			// 存到数据库的是密文，将输入的明文通过加密方式加密进行对比，一样则成功，否则失败
			throw new AccountException("密码不正确");
		}
		return new SimpleAuthenticationInfo(token.getPrincipal(), password,
				ByteSource.Util.bytes(inputUserName + "salt"), getName());
	}

	/**
	 * 
	 * @描述: 获取授权信息
	 * @方法名: doGetAuthorizationInfo
	 * @param principalCollection
	 * @return
	 * @创建人：T-liul4
	 * @创建时间：2019年7月23日上午9:59:29
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// TODO Auto-generated method stub
		log.info("权限认证方法：doGetAuthorizationInfo");

		String username = (String) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 设置角色
		Set<String> roles = sysUserDao.getRoles(username);
		info.setRoles(roles);

		// 设置权限
		Set<String> stringSet = new HashSet<>();
		stringSet.add("user:show");
		info.setStringPermissions(stringSet);

		return info;
	}

}