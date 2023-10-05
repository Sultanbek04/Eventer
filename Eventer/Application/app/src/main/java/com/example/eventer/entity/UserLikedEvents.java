package com.example.eventer.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLikedEvents {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("userId")
    private int userId;

    @Expose
    @SerializedName("likedEventId")
    private int likedEventId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public UserLikedEvents(int userId, int likedEventId) {
        this.userId = userId;
        this.likedEventId = likedEventId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikedEventId() {
        return likedEventId;
    }

    public void setLikedEventId(int likedEventId) {
        this.likedEventId = likedEventId;
    }

    public UserLikedEvents(int id, int userId, int likedEventId) {
        this.id = id;
        this.userId = userId;
        this.likedEventId = likedEventId;
    }
}
