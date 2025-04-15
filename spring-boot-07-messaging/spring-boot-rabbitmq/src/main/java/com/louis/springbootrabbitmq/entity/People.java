package com.louis.springbootrabbitmq.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Louis
 * @title: People
 * @projectName springbootstudy
 * @description: TODO
 */
public class People implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private List<Pet> petList = new ArrayList<>();
    ;

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", petList=" + petList +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    ;

}
