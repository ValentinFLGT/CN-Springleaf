package com.campusnumerique.campus.model;

public class Character {

    private Integer id;
    private String name;
    private String type;

    public Character(){}

    public Character(String name, String type) {
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Character { name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

