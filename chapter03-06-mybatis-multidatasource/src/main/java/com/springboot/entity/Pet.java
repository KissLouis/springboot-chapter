package com.springboot.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author Louis
 * @title: Pet
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 15:05
 */
@Data
public class Pet {

    private Integer petId;
    private String petName;
    private Integer breed;
    private Date createTime;

}
