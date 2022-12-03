package com.booktree.model;

import java.io.Serializable;

public class Friend implements Serializable {
    public String Followers;
    public String UserId;


    public Friend() {}

    public String getFollowers(){
        return Followers;
    }
    public void setFollowers(String Followers){
        this.Followers=Followers;
    }
    public String getUserId(){
        return UserId;
    }
    public void setUserId(String UserId){
        this.UserId=UserId;
    }
}

