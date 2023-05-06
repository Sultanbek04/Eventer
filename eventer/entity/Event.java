package com.example.eventer.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @Expose
    @SerializedName("id")
    private int id;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getCityId() {
        return cityId;
    }


    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("sectionId")
    private int sectionId;

    @Expose
    @SerializedName("userId")

    private int userId;

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }


    @Expose
    @SerializedName("cityId")
    private int cityId;
    @Expose
    @SerializedName("eventDate")
    private String eventDate;
    @Expose
    @SerializedName("eventTime")
    private String eventTime;

    @Expose
    @SerializedName("numberOfLikes")
    private long numberOfLikes;

    @Expose
    @SerializedName("checked")
    private boolean checked;

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Expose
    @SerializedName("imagePath")
    private String imagePath;

    public Event() {
    }

    public Event(int id, String name, String description, int sectionId, int userId, int cityId, String eventDate, String eventTime, boolean checked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sectionId = sectionId;
        this.userId = userId;
        this.cityId = cityId;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.checked = checked;
    }

    public long getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Event(String name, String description, int sectionId, int userId, int cityId, String eventDate, String eventTime, boolean checked
            , String imagePath, int numberOfLikes) {
        this.name = name;
        this.description = description;
        this.sectionId = sectionId;
        this.cityId = cityId;
        this.checked = checked;
        this.userId = userId;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.imagePath = imagePath;
        this.numberOfLikes = numberOfLikes;
    }

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sectionId=" + sectionId +
                ", userId=" + userId +
                ", cityName='" + cityId + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSection_id() {
        return sectionId;
    }

    public void setSection_id(int section_id) {
        this.sectionId = section_id;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public Event(String name, String description, int section_id, int user_id) {
        this.name = name;
        this.description = description;
        this.sectionId = section_id;
        this.userId = user_id;
    }


}
