package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/guest")
public class GuestController {

	@GetMapping("/getMessage")
	public Result getMessage() {
		return new Result(ResultCode.SUCCESS, "你拥有Guest用户的信息权限！");
	}

}
