package com.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author Louis
 * @title: People
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 15:02
 */
@Data
@Document(collection = "people") // 数据库集合名称
public class People implements Serializable {

	private static final long serialVersionUID = -2467817939988375785L;
	private String _id;
	// 自增ID
	@Id
	private Integer peopleId;
	private String peopleName;
	private String password;
	private String salt;
	private Integer status;
	private Date modifyTime;
	private Date createTime;

	public void setCreateTime(Date createTime) {
		this.modifyTime = createTime;
		this.createTime = createTime;
	}

	public People(Integer peopleId) {
		super();
		this.peopleId = peopleId;
	}

}