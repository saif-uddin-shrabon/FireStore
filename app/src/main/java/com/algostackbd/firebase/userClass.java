package com.algostackbd.firebase;

public class userClass {
  private String fullname,username,pnumber,email;

    public userClass(String fullname, String username, String pnumber, String email) {
        this.fullname = fullname;
        this.username = username;
        this.pnumber = pnumber;
        this.email = email;
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

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
