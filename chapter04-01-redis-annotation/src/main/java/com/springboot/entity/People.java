package com.springboot.entity;

import java.io.Serializable;
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
public class People implements Serializable {

	private static final long serialVersionUID = -2467817939988375785L;
	private Integer peopleId;

	public Integer getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(Integer peopleId) {
		this.peopleId = peopleId;
	}

	private String peopleName;
	private String password;
	private String salt;
	private Integer status;
	private Date modifyTime;
	private Date createTime;

}