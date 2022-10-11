package com.ldt.apptonghop;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int userImg;
    private String userName;
    private String userPassword;
    private String userFullName;

    public User(int userImg, String userName, String userPassword, String userFullName) {
        this.userImg = userImg;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFullName = userFullName;
    }

    public User(String userName, String userPassword, String userFullName) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFullName = userFullName;
    }

    public User() {
    }

    protected User(Parcel in) {
        userImg = in.readInt();
        userName = in.readString();
        userPassword = in.readString();
        userFullName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getUserImg() {
        return userImg;
    }

    public void setUserImg(int userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userImg);
        parcel.writeString(userName);
        parcel.writeString(userPassword);
        parcel.writeString(userFullName);
    }
}
