package com.ldt.listview_food;

public class Food {
    private int img;
    private String name;
    private String description;
    private String price;

    public Food() {
    }

    public Food(int img, String name, String description, String price) {
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
