package com.springboot.exception;

/**
 * @author Louis
 * @title: ServiceException
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/23 21:23
 */
public class ServiceException extends Exception {

    public ServiceException(String msg, Exception e) {
        super(msg + "\n" + e.getMessage());
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
