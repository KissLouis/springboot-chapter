package com.louis.springbootfreemarker.entity;

/**
 * @author Louis
 * @title: Pet
 * @projectName springbootstudy
 * @description: TODO
 */
public class Pet {

    private String breed;
    private String name;

    @Override
    public String toString() {
        return "Pet{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
