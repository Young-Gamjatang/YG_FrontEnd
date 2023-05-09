package com.example.test.Retrofitmanager;

import com.google.gson.annotations.SerializedName;

public class ResModel {

    @SerializedName("groupname")
    private String groupname;

    @SerializedName("groupcode")
    private String groupcode;

    @SerializedName("password")
    private String password;

    @SerializedName("group_created_at")
    private String group_created_at;

    @SerializedName("group_updated_at")
    private String group_updated_at;

    public ResModel(String groupname, String password) {
        this.groupname = groupname;
        this.password = password;
    }





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

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "" +
                "groupname:'" + groupname + ",\'" +
                "groupcode:'" + groupcode + '\'' +
                "";
    }

}
