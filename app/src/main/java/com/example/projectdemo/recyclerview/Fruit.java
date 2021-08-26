package com.example.projectdemo.recyclerview;

public class Fruit {
    private final int imageId;
    private final String name;

    public Fruit(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
