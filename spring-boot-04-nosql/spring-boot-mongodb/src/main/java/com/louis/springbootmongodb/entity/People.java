package com.louis.springbootmongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Louis
 * @title: People
 * @projectName springbootstudy
 */
@Data
@Document(value="people")
public class People implements Serializable {

    @Id
    private Integer id;
    private String name;
    private Integer age;

}
