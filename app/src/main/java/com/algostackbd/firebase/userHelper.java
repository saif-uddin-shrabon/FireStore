package com.algostackbd.firebase;

public class userHelper {
  String Name,Dept,Batch,ID,Sec;

    public userHelper(String name, String dept, String batch, String ID, String sec) {
        Name = name;
        Dept = dept;
        Batch = batch;
        this.ID = ID;
        Sec = sec;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSec() {
        return Sec;
    }

    public void setSec(String sec) {
        Sec = sec;
    }
}
