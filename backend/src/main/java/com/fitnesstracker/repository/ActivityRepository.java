package com.fitnesstracker.repository;

import com.fitnesstracker.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByUserId(String userId);
    List<Activity> findByUserIdAndActivityDateBetween(String userId, LocalDateTime startDate, LocalDateTime endDate);
    List<Activity> findByUserIdAndActivityType(String userId, String activityType);
}
