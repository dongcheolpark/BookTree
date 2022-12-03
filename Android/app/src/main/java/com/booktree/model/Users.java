package com.booktree.model;

import java.io.Serializable;

public class Users implements Serializable{
    public String profile;
    public String id;
    public String userName;

    public Users() {
    }

    public Users(String profile, String id, String userName){
        this.id=id;
        this.userName=userName;
        this.profile=profile;
    }
    /*public String getProfile(){
        return profile;
    }

    public void setProfile(String profile){
        this.profile=profile;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }*/
}


