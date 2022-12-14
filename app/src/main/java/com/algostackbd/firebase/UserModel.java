package com.algostackbd.firebase;

public class UserModel {
    private String fullname,username,phoneNumber,email,purl;

    public UserModel(String fullname, String username, String phoneNumber, String email, String purl) {
        this.fullname = fullname;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.purl = purl;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
