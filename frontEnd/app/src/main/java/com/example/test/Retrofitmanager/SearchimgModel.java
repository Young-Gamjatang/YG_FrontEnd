package com.example.test.Retrofitmanager;

import com.google.gson.annotations.SerializedName;

public class SearchimgModel {
    @SerializedName("image_url")
    private String image_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "SearchimgModel{" +
                "image_url='" + image_url + '\'' +
                '}';
    }
}
