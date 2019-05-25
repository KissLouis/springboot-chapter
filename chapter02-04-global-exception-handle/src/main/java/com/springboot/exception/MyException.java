package com.springboot.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Louis
 * @title: MyException
 * @projectName springboot-study
 * @description: TODO
 * @date 2019/5/25 16:53
 */
public class MyException extends RuntimeException {

    @Getter
    @Setter
    private String code;
    private String message;

    public MyException(String code, String message) {
        super(message);
        this.code = code;
    }
}
