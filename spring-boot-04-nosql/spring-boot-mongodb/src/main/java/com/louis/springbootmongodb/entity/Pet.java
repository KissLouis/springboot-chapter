package com.louis.springbootmongodb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Louis
 * @title: Pet
 * @projectName springboot-chapter
 */
@Data
@Document(value = "pet")
public class Pet implements Serializable {

    @Id
    private Integer id;
    private String petName;
    @JsonIgnoreProperties(value = "petList")
    private People people;

}
