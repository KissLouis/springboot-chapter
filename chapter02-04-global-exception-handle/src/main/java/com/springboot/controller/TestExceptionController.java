package com.springboot.controller;

import com.springboot.exception.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

@RestController
public class TestExceptionController {


    /**
     *
     * @description: //TODO 自定义全局异常
     * @author Louis
     * @date 2019/5/25 17:16
     * @return void
     */
    @RequestMapping(value = "/ex")
    public void testException() throws Exception{
        throw new MyException("500","自定义全局错误");
    }

    /**
     *
     * @description: //TODO 系统异常
     * @author Louis
     * @date 2019/5/25 17:16
     * @return java.lang.String
     */
    @RequestMapping("/ex1")
    public String testException1() {
        Integer a = 1 / 0;
        return "系统异常 Exception";
    }

}
