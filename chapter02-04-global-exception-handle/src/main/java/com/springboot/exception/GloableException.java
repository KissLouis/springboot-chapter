package com.springboot.exception;

import com.springboot.util.Result;
import com.springboot.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Louis
 * @title: GloableException
 * @projectName springboot-study
 * @description: TODO
 * @date 2019/5/25 16:54
 */
@ControllerAdvice//是controller 的一个辅助类，最常用的就是作为全局异常处理的切面类
public class GloableException {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(value = Exception.class)//表示异常拦截
    @ResponseBody
    public Result Handle(Exception e) {
        logger.error("exception", e);
        if (e instanceof MyException) {
            //自定义异常
            MyException myException = (MyException) e;
            return ResultUtil.error(myException.getCode(), myException.getMessage());
        }
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return ResultUtil.error("404", e.getMessage());
        }
        return ResultUtil.error("1000", e.getMessage());
    }

}
