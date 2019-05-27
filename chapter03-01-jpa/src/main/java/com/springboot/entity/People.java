package com.springboot.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
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
public class People {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "peopleId")
    private String peopleId;
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
    @CreationTimestamp
    @Column(name = "lastLoginTime", columnDefinition = "datetime COMMENT '上一次登陆时间' ")
    private Date lastLoginTime;
    @CreationTimestamp
    @Column(name = "createTime", columnDefinition = "timestamp COMMENT '创建时间'")
    private Date createTime;

}
