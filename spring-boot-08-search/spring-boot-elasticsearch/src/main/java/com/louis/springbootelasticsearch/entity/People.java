package com.louis.springbootelasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Louis
 * @title: People
 * @projectName spring-boot-elasticsearch
 */
@Document(indexName = "people_index")
public class People implements Serializable {

    @Id
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
