package com.ldt.movielistcrud.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Movie implements Serializable {
    private String imgUrl;
    private String Name;
    private String Duration;
    private String Categories;
    private int ProductionYear;
    private String Description;
    private Double rate;
    private boolean isFavorite = false;

    public Movie() {
    }

    public Movie(String imgUrl, String name, String duration, String categories, int productionYear, String description, Double rate, boolean isFavorite) {
        this.imgUrl = imgUrl;
        Name = name;
        Duration = duration;
        Categories = categories;
        ProductionYear = productionYear;
        Description = description;
        this.rate = rate;
        this.isFavorite = isFavorite;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    public int getProductionYear() {
        return ProductionYear;
    }

    public void setProductionYear(int productionYear) {
        ProductionYear = productionYear;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
