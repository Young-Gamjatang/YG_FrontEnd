package com.example.test.Retrofitmanager;

import com.google.gson.annotations.SerializedName;

public class ReslistModel {

    @SerializedName("groupname")
    private String groupname;

    @SerializedName("groupcode")
    private String groupcode;




    public String getgroupname() {return groupname;}

    public void setgroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getgroupcode() {
        return groupcode;
    }

    public void setgroupcode(String groupcode) {
        this.groupcode = groupcode;
    }

    public String toString() {
        return "GroupModel{" +
                "groupname='" + groupname + '\'' +
                "groupcode='" + groupcode + '\'' +
                '}';
    }

}
