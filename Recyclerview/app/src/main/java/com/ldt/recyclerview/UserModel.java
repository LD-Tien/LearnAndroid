package com.ldt.recyclerview;

public class UserModel {
    private String userName;

    public UserModel(String userName) {
        this.userName = userName;
    }

    public UserModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
