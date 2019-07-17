package com.springboot.security.config;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.security.passwordencoder.MyPasswordEncoder;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyPasswordEncoder myPasswordEncoder;

	@Autowired
	private UserDetailsService myCustomUserService;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				// 允许不登陆就可以访问的方法，多个用逗号分隔
				.antMatchers("/static/**", "/webjars/**", "/public/**", "/login", "/register", "/favicon.ico")
				.permitAll()
				// 访问/user 需要拥有admin权限
				.antMatchers("/user/**").hasAuthority("ROLE_USER")
				// 其他的需要授权后访问
				.anyRequest().authenticated();

		// 开启"记住我"功能
		// http.rememberMe().tokenRepository(persistentTokenRepository());

		// 单用户登录，如果有一个登录了，同一个用户在其他地方登录将前一个剔除下线
		// http.sessionManagement().maximumSessions(1).expiredSessionStrategy(expiredSessionStrategy());
		// 单用户登录，如果有一个登录了，同一个用户在其他地方不能登录
		// http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);

		http.authenticationProvider(authenticationProvider()).httpBasic()
				// 未登录时，进行json格式的提示，很喜欢这种写法，不用单独写一个又一个的类
				.authenticationEntryPoint((request, response, authException) -> {
					response.setContentType("application/json;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					PrintWriter out = response.getWriter();
					Result result = new Result(ResultCode.UNAUTHENTICATED);
					out.write(objectMapper.writeValueAsString(result));
					out.flush();
					out.close();
				});

		http.formLogin() // 使用自带的登录
				.permitAll()
				// 登录失败，返回json
				.failureHandler((request, response, ex) -> {
					response.setContentType("application/json;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					PrintWriter out = response.getWriter();
					Result result = null;
					if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
						result = new Result(401, "用户名或密码错误：" + ex.getMessage(), false);
					} else if (ex instanceof DisabledException) {
						result = new Result(401, "账户被禁用：" + ex.getMessage(), false);
					} else {
						result = new Result(401, "登录失败：" + ex.getMessage(), false);
					}
					out.write(objectMapper.writeValueAsString(result));
					out.flush();
					out.close();
				})
				// 登录成功，返回json
				.successHandler((request, response, authentication) -> {
					Result result = new Result(ResultCode.LOGIN_SUCCESS, authentication);
					response.setContentType("application/json;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.write(objectMapper.writeValueAsString(result));
					out.flush();
					out.close();
				}).and().exceptionHandling()
				// 没有权限，返回json
				.accessDeniedHandler((request, response, ex) -> {
					response.setContentType("application/json;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					PrintWriter out = response.getWriter();
					Result result = new Result(ResultCode.UNAUTHORISE);
					out.write(objectMapper.writeValueAsString(result));
					out.flush();
					out.close();
				}).and().logout()
				// 退出成功，返回json
				.logoutSuccessHandler((request, response, authentication) -> {
					Result result = new Result(ResultCode.LOGOUT_SUCCESS);
					response.setContentType("application/json;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.write(objectMapper.writeValueAsString(result));
					out.flush();
					out.close();
				}).permitAll();

		// 开启跨域访问
		http.cors().disable();
		// 开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
		http.csrf().disable();

	}

	@Override
	public void configure(WebSecurity web) {
		// 对于在header里面增加token等类似情况，放行所有OPTIONS请求。
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		// 对默认的UserDetailsService进行覆盖
		authenticationProvider.setUserDetailsService(myCustomUserService);
		authenticationProvider.setPasswordEncoder(myPasswordEncoder);
		return authenticationProvider;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.authenticationProvider());
		auth.userDetailsService(myCustomUserService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
