package com.springboot.controller.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.SysUser;
import com.springboot.service.SysUserService;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;

/**
 * 
 * @类描述： 登陆等处理的 LoginController
 * 
 * @项目名称：chapter07-03-shiro @包名： com.springboot.controller.login
 * @类名称：LoginController
 * @创建人：Louis
 * @创建时间：2019年7月23日上午10:46:41
 */
@RestController
public class LoginController {

	@Autowired
	private SysUserService sysUserService;

	@GetMapping("/notLogin")
	public Result login() {
		return new Result(ResultCode.UNAUTHENTICATED, "默认未登录跳转请求");
	}

	@GetMapping(value = "/notRole")
	public Result notRole() {
		return new Result(ResultCode.UNAUTHORISE);
	}

	@GetMapping(value = "/logout")
	public Result logout() {
		Subject subject = SecurityUtils.getSubject();
		// 注销
		subject.logout();
		return new Result(ResultCode.LOGOUT_SUCCESS);
	}

	@PostMapping("/login")
	public Result login(String username, String password) {
		// 从SecurityUtils里边创建一个 subject
		Subject subject = SecurityUtils.getSubject();
		// 在认证提交前准备 token（令牌）
		// password = MD5Util.MD5Pwd(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 执行认证登陆

		String message = "";

		try {
			subject.login(token);
		} catch (UnknownAccountException uae) {
			message = "未知账户" + uae.getMessage();
		} catch (IncorrectCredentialsException ice) {
			message = "密码不正确" + ice.getMessage();
		} catch (LockedAccountException lae) {
			message = "账户已锁定" + lae.getMessage();
		} catch (ExcessiveAttemptsException eae) {
			message = "用户名或密码错误次数过多" + eae.getMessage();
		} catch (AuthenticationException ae) {
			message = "用户名或密码不正确！" + ae.getMessage();
		}

		if (subject.isAuthenticated()) {
			return new Result(ResultCode.LOGIN_SUCCESS, token);
		} else {
			token.clear();
			return new Result(ResultCode.LOGIN_FAIL, message);
		}
	}

	@PostMapping("/register")
	public Result doRegister(SysUser user) {
		// 此处省略校验逻辑
		sysUserService.insert(user);
		return new Result(ResultCode.SUCCESS);
	}

}
