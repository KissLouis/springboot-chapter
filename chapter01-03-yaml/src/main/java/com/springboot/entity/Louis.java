package com.springboot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Louis
 * @title: Louis
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/14 14:41
 */
@Component
@ConfigurationProperties(prefix = "com.louis")
public class Louis {

    private String name;
    private String petName;
    private Map user;
    private List<String> type;
    private Pet pet;

    @Override
    public String toString() {
        return "Louis{" +
                "name='" + name + '\'' +
                ", petName='" + petName + '\'' +
                ", user=" + user +
                ", type=" + type +
                ", pet=" + pet +
                '}';
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getUser() {
        return user;
    }

    public void setUser(Map user) {
        this.user = user;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
