package com.louis.springbootredis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Louis
 * @title: Pet
 * @projectName springbootstudy
 * @description: TODO
 */
public class Pet implements Serializable {

    private Integer id;
    private String petName;
    @JsonIgnoreProperties(value="petList")
    private People people;


    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", petName='" + petName + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Integer getId() {
        return id;
    }

    public String getPetName() {
        return petName;
    }

    public People getPeople() {
        return people;
    }
}
