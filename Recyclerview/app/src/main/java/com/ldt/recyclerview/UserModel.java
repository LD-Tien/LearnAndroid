package com.ldt.recyclerview;

import java.io.Serializable;

public class UserModel implements Serializable {
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
