package com.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Louis
 * @title: People
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/14 14:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {

    private Integer peopleId;
    private String peopleName;
    private String password;
    private String salt;
    private Integer status;
    private Date modifyTime;
    private Date createTime;

}
