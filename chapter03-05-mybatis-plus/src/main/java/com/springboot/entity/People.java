package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Louis
 * @since 2019-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class People implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "peopleId", type = IdType.AUTO)
    private Integer peopleId;

    @TableField("peopleName")
    private String peopleName;

    private String password;

    /**
     * salt值
     */
    private String salt;

    /**
     * 用户当前状态
     * 0：异常
     * 1：正常
     */
    private Integer status;

    @TableField("modifyTime")
    private Date modifyTime;

    @TableField("createTime")
    private Date createTime;


}
