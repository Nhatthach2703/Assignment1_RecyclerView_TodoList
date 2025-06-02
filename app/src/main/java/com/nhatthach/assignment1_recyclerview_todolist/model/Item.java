package com.nhatthach.assignment1_recyclerview_todolist.model;

public class Item {
    private String name;
    private int imageResId;
    public Item(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getImageResId() {
        return imageResId;
    }
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
