package com.springboot.entity;

import lombok.Data;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Louis
 * @title: People
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 15:02
 */
@Data
@Entity
@Table(name = "people")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class People {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "peopleId")
	private Integer peopleId;

	@Column(name = "peopleName")
	private String peopleName;
	/**
	 * 省略默认列名就是属性名
	 */
	@Column(name = "password")
	private String password;

	private String salt;

	@Column(name = "status", columnDefinition = "int COMMENT '用户当前状态 0：异常 1：正常'")
	private Integer status;

	@LastModifiedDate
	@Column(name = "modifyTime")
	private Date modifyTime;

	@CreatedDate
	@Column(name = "createTime", columnDefinition = "timestamp COMMENT '创建时间'")
	private Date createTime;

}
