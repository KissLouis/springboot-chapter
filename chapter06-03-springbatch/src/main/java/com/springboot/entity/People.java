package com.springboot.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @类描述：People实体类
 * @项目名称：chapter08-03-springbatch @包名： com.springboot.entity
 * @类名称：People
 * @创建人：Louis
 * @创建时间：2019年7月11日下午4:26:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {

	private Integer peopleId;
	private String peopleName;
	private String password;
	private String salt;
	private Integer status;
	private Date modifyTime;
	private Date createTime;

}