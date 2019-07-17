package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.SysUser;
import com.springboot.service.SysUserService;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;

/**
 * 
 * @项目名称：chapter07-01-springsecurity @包名： com.springboot.controller
 * @类名称：SysUserController
 * @创建人：Louis
 * @创建时间：2019年7月17日上午10:53:15
 */
@RestController
@RequestMapping("/user/")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	/**
	 * @描述: 用户注册
	 * @方法名: doRegister
	 * @param user
	 * @return
	 * @返回类型 Result
	 * @创建人 T-liul4
	 * @创建时间 2019年7月17日下午2:15:06
	 */
	@PostMapping("/register")
	public Result doRegister(SysUser user) {
		// 此处省略校验逻辑
		sysUserService.insert(user);
		return new Result(ResultCode.SUCCESS);
	}

	/**
	 * @描述: 模拟进行查询操作
	 * @方法名: findInfo
	 * @return
	 * @返回类型 Result
	 * @创建人 T-liul4
	 * @创建时间 2019年7月17日下午2:14:47
	 */
	@GetMapping("/findInfo")
	public Result findInfo() {
		// 模拟查询用户操作
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new Result(ResultCode.SUCCESS, userDetails.getAuthorities());
	}

}
