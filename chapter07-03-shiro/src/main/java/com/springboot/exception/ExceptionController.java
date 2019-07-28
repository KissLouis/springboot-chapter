package com.springboot.exception;

import org.apache.shiro.authc.AccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(AccountException.class)
	public Result handleShiroException(Exception ex) {
		return new Result(ResultCode.FAIL, ex.getMessage());
	}

}
