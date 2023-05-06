package com.example.serverandroid.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "section_id")
    private int sectionId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "city_id")
    private int cityId;
    @Column(name = "event_date")
    private String eventDate;
    @Column(name = "event_time")
    private String eventTime;
    @Column(name = "checked")
    private boolean checked;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "number_of_likes")
    private Integer numberOfLikes;

}
