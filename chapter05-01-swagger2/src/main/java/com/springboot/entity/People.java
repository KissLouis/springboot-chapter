package com.springboot.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @ApiModelProperty(value = "编号")
    private Integer peopleId;
    @ApiModelProperty(value = "名称")
    private String peopleName;
    private String password;
    private String salt;
    private Integer status;
    private Date modifyTime;
    private Date createTime;

}
