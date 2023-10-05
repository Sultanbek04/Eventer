package com.example.eventer.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventImage {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("image_id")
    private int image_id;

    @Expose
    @SerializedName("image_path")
    private String image_path;

    @Override
    public String toString() {
        return "EventImage{" +
                "id=" + id +
                ", image_id=" + image_id +
                ", image_path='" + image_path + '\'' +
                '}';
    }

    public EventImage(int id, int image_id, String image_path) {
        this.id = id;
        this.image_id = image_id;
        this.image_path = image_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
