package com.louis.springbootjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

/**
 * @author Louis
 * @title: Pet
 * @projectName springbootstudy
 * @description: TODO
 */
@Entity
@Table(name = "pet")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "pet_name")
    private String petName;
    @ManyToOne
    @JoinColumn(name = "people_id")
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
