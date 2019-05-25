package com.springboot.entity;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Louis
 * @title: People
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/14 14:41
 */
@Component
@ConfigurationProperties(prefix = "com.people")
/*
    @Getter
    @Setter
    @ToString
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {

    private String name;
    private String petName;
    private Map user;
    private List<String> type;
    private Pet pet;

}

