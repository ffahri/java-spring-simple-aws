package com.webischia.api.model;


public class TestModel {
    private String Id;
    private String name;
    private int value;

    public TestModel(String id, String name, int value) {
        Id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

