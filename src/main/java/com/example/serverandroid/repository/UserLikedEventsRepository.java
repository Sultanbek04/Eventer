package com.example.serverandroid.repository;

import com.example.serverandroid.entity.Event;
import com.example.serverandroid.entity.UserLikedEvents;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface UserLikedEventsRepository extends JpaRepository<UserLikedEvents, Integer> {

    public static final String GET_EVENT_ID = "SELECT liked_event_id FROM user_liked_events WHERE user_id=?1";
    public static final String GET_EVENTS_BY_EVENT_ID = "select e. * from events e " +
            "inner join user_liked_events u on u.liked_event_id =e.id\n" +
            " and u.user_id= e.user_id where e.user_id=?1";


    @Transactional
    void deleteByLikedEventId(@PathVariable("likedEventId") int likedEventId);

    @Transactional
    @Query(value = GET_EVENT_ID, nativeQuery = true)
    List<Integer> getLikedEventIdByUserId(@Param("userId") Integer userId);



}
