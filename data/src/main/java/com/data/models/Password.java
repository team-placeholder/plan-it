package com.data.models;

/**
 * Created by Antoan on 2/21/2017.
 */

public class Password {
    private String oldPassword;
    private String newPassword;
    private String email;

    public Password(){

    }

    public Password(String email, String oldPassword, String newPassword){
        this.setOldPassword(oldPassword);
        this.setNewPassword(newPassword);
        this.setEmail(email);
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
