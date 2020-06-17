package com.example.mydynamiccontrol.box;

public class MyModel {

    private int id;
    private String name;
    private int visibility;

    public MyModel(int id, String name, int visibility) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
}
