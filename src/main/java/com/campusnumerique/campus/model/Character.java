package com.campusnumerique.campus.model;

public class Character {

    private Integer id;
    private String name;
    private String gender;

    public Character(){}

    public Character(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Character { name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

