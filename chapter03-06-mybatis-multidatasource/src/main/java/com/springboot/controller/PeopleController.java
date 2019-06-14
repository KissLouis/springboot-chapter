package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.PeopleService;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;

/**
 * @author Louis
 * @title: PeopleController
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/28 10:45
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PeopleService peopleService;

	/**
	 * @return com.springboot.util.result.Result
	 * @description: //TODO 查询全部用户
	 * @author Louis
	 * @date 2019/5/28 10:49
	 */
	@GetMapping("")
	public Result findAll() {
		return new Result(ResultCode.SUCCESS, peopleService.findAll());
	}

	/**
	 * @param [id]
	 * @return com.springboot.util.result.Result
	 * @description: //TODO 根据Id查询用户
	 * @author Louis
	 * @date 2019/5/28 10:49
	 */
	@GetMapping("/{id}")
	public Result getOne(@PathVariable("id") Integer id, HttpServletRequest request) {
		HttpSession session = request.getSession();// 这就是session的创建
		session.setAttribute("id", id);// 给session添加id属性,判断Druid是否有Session监控
		return new Result(ResultCode.SUCCESS, peopleService.findById(id));
	}

}
