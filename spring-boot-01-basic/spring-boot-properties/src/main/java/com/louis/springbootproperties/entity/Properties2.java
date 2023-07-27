package com.louis.springbootproperties.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 第二种方式：使用@Value注解方式（该方式仅适用于String类型）
 */
@Component
public class Properties2 {

    @Value("${com.louis.name}")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PropertiesEntity1{" +
                "name='" + name + '\'' +
                '}';
    }
}
