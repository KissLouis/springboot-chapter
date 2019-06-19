package com.springboot.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Louis
 * @title: Pet
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 15:05
 */
@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue
    @Column(name = "petId")
    private String petId;

    @Column(name = "petName")
    private String petName;

    @Column(name = "breed", columnDefinition = "int COMMENT '宠物品种1：宠物猪 2：宠物狗 3：猫咪'")
    private Integer breed;

    @LastModifiedDate
    @Column(name = "modifyTime")
    private Date modifyTime;

    @CreatedDate
    @Column(name = "createTime", columnDefinition = "timestamp COMMENT '创建时间'")
    private Date createTime;

}
