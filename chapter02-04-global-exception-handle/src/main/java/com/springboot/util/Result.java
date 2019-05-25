package com.springboot.util;

import lombok.Data;

/**
 * @author Louis
 * @title: result
 * @projectName springboot-study
 * @description: TODO
 * @date 2019/5/25 16:50
 */
@Data
public class Result {
    private boolean status;
    private String code;
    private Object data;
    private String message;
}
