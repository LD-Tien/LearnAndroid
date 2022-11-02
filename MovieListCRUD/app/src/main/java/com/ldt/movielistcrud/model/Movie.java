package com.ldt.movielistcrud.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int imgResource;
    private String Name;
    private String Duration;
    private String Categories;
    private Short ProductionYear;
    private String Description;
    private Double rate;
    private boolean isFavorite = false;

    public Movie(int imgResource, String name, String duration, String categories, Short productionYear, String description, Double rate, boolean isFavorite) {
        this.imgResource = imgResource;
        Name = name;
        Duration = duration;
        Categories = categories;
        ProductionYear = productionYear;
        Description = description;
        this.rate = rate;
        this.isFavorite = isFavorite;
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        imgResource = in.readInt();
        Name = in.readString();
        Duration = in.readString();
        Categories = in.readString();
        int tmpProductionYear = in.readInt();
        ProductionYear = tmpProductionYear != Integer.MAX_VALUE ? (short) tmpProductionYear : null;
        Description = in.readString();
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readDouble();
        }
        isFavorite = in.readByte() != 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
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

    public Short getProductionYear() {
        return ProductionYear;
    }

    public void setProductionYear(Short productionYear) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imgResource);
        parcel.writeString(Name);
        parcel.writeString(Duration);
        parcel.writeString(Categories);
        parcel.writeInt(ProductionYear != null ? (int) ProductionYear : Integer.MAX_VALUE);
        parcel.writeString(Description);
        if (rate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rate);
        }
        parcel.writeByte((byte) (isFavorite ? 1 : 0));
    }
}
