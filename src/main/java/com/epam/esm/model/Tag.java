package com.epam.esm.model;


public class Tag {
    long id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }
    public Tag() {}

    public Tag(long id, String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
