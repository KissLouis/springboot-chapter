package com.louis.springbootelasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author Louis
 * @title: Pet
 * @projectName spring-boot-elasticsearch
 */
@Document(indexName = "pet_index")
public class Pet implements Serializable {

    @Id
    private Integer id;
    @Field(type = FieldType.Text)
    private String petName;
    @JsonIgnoreProperties("petList")
    @Field(type = FieldType.Object, ignoreFields = {"petList"})
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
