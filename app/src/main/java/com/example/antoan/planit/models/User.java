package com.example.antoan.planit.models;

/**
 * Created by Antoan on 2/18/2017.
 */

public class User {
    private String username;
    private String email;
    private String password;
    private String avatar;
    private int newRequest;
    private Boolean isFriend;

    public User(){

    }

    public User(String username, String email, String password, String avatar){
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.isFriend = true;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getNewRequest() {
        return newRequest;
    }

    public void setNewRequest(int newRequest) {
        this.newRequest = newRequest;
    }

    public Boolean isFriend() {
        return isFriend;
    }

    public void setFriend(Boolean friend) {
        isFriend = friend;
    }
}
