package com.springboot.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Louis
 * @title: ValidateCodeException
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/30 17:50
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 5206503863206533537L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
