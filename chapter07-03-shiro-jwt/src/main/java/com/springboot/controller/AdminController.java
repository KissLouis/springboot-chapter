package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/getMessage")
	public Result getMessage() {
		return new Result(ResultCode.SUCCESS, "你拥有Admin用户的信息权限！");
	}

}
