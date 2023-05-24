package com.example.coffeeshop.DTO;

public class Category {
    int id;
    String name;

    public Category(String name, int id) {
        this.name = name;
        this.id  = id;
    }

    public Category() {
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }
    public String getNameCategory() {
        return name;
    }
    public void setNameCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
