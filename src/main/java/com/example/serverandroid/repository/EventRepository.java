package com.example.serverandroid.repository;

import com.example.serverandroid.entity.Event;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
//    List<Event> findAllByCountryIdAndCityNameAndSectionId(int countryId, String cityName, int sectionId);

//    List<Event> findAllByCountryIdAndCityName(int countryId, String cityName);

    public static final String GET_NUMBER_OF_LIKES = "SELECT number_of_likes FROM events WHERE id=?1";

    List<Event> findAllByUserId(int userId);

    List<Event> findAllByCheckedIsTrue();

    List<Event> getAllByCheckedIsFalse();

    @Transactional
    List<Event> getAllByCheckedIsTrue();

    @Transactional
    @Query(value = GET_NUMBER_OF_LIKES, nativeQuery = true)
    int getNumberOfLikesWithSpecificId(@Param("id") Integer id);

    @Transactional
    List<Event> getEventsByCityId(@Param("cityId") Integer cityId);

    @Transactional
    List<Event> getEventsByCityIdAndCheckedIsTrue(@Param("cityId") Integer cityId);


    @Transactional
    List<Event> getEventsByCityIdAndSectionIdAndCheckedIsTrue(@Param("cityId") int cityId, @Param("sectionId") int sectionId);

    @Transactional
    List<Event> getEventsByCityIdAndSectionId(@Param("cityId") Integer cityId, @Param("sectionId") Integer sectionId);

    @Modifying
    @Query("update Event e set e.checked=true where e.id = ?1")
    void updateCheckedToTrue(@Param("id") Integer id);

    @Modifying
    @Query("update Event  e set e.imagePath=?1 where e.id=?2")
    @Transactional
    void updateImagePathOfAnEvent(@Param("imagePath") String imagePath, @Param("id") int id);

    @Modifying
    @Query("update Event e set e.numberOfLikes=e.numberOfLikes+1 where e.id=?1")
    @Transactional
    void incrementNumberOfLikesOfEvent(@Param("id") Integer id);

    @Modifying
    @Query("update Event e set e.numberOfLikes=e.numberOfLikes-1 where e.id=?1")
    @Transactional
    void decrementNumberOfLikesOfEvent(@Param("id") Integer id);


    public static final String GET_EVENTS_BY_EVENT_ID = "select e. * from events e " +
            "inner join user_liked_events u on u.liked_event_id =e.id\n" +
            " where u.user_id=?1";

    @Query(value = GET_EVENTS_BY_EVENT_ID, nativeQuery = true)
    @Transactional
    ArrayList<Event> getEventsOfUser(@Param("userId") Integer userId);
}
