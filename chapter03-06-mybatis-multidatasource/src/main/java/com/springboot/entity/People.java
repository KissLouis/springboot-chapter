package com.springboot.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author Louis
 * @title: People
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 15:02
 */
@Data
public class People {

	private Integer peopleId;
	private String peopleName;
	private String password;
	private String salt;
	private Integer status;
	private Date modifyTime;
	private Date createTime;
	private Pet pet;

}