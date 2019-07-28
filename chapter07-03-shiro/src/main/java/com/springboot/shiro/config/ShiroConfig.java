package com.springboot.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.shiro.realm.CustomRealm;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @类描述：shiro的一些配置。 包括：过滤的文件和权限，密码加密的算法，其用注解等相关功能。 @项目名称：chapter07-03-shiro @包名：
 *                  com.springboot.shiro.config
 * @类名称：ShiroConfig
 * @创建人：Louis
 * @创建时间：2019年7月23日上午9:50:33
 */
@Configuration
@Slf4j
public class ShiroConfig {

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
		shiroFilterFactoryBean.setLoginUrl("/notLogin");
		// 设置无权限时跳转的 url;
		shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

		// 设置拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 游客，开发权限
		// uthc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		filterChainDefinitionMap.put("/guest/**", "anon");
		// 用户，需要角色权限 “ROLE_ADMIN”
		filterChainDefinitionMap.put("/user/**", "roles[ROLE_USER]");
		// 管理员，需要角色权限 “ROLE_ADMIN”
		filterChainDefinitionMap.put("/admin/**", "roles[ROLE_ADMIN]");
		// 开放登陆接口
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/register", "anon");
		// 其余接口一律拦截
		// 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
		filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		log.info("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}

	/**
	 * 
	 * @描述: 注入securityManager
	 * @方法名: securityManager
	 * @return
	 * @返回类型 SecurityManager
	 * @创建人 T-liul4
	 * @创建时间 2019年7月23日上午9:54:46
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(customRealm());
		return securityManager;
	}

	/**
	 * 自定义身份认证 realm;
	 * <p>
	 * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm， 否则会影响 CustomRealm类 中其他类的依赖注入
	 */
	@Bean
	public CustomRealm customRealm() {
		CustomRealm customRealm = new CustomRealm();
		customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		customRealm.setCachingEnabled(false);
		return customRealm;
	}

	/**
	 * 
	 * @描述: 密码加密
	 * @方法名: hashedCredentialsMatcher
	 * @return
	 * @返回类型 HashedCredentialsMatcher
	 * @创建人 T-liul4
	 * @创建时间 2019年7月23日下午2:13:10
	 */
	@Bean(name = "credentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		// 散列的次数，比如散列两次，相当于 md5(md5(""));
		hashedCredentialsMatcher.setHashIterations(2);
		// storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

}
