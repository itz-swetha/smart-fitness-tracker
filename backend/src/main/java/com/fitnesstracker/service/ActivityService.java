package com.fitnesstracker.service;

import com.fitnesstracker.model.Activity;
import com.fitnesstracker.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity createActivity(Activity activity, double userWeight) {
        activity.setCaloriesBurned(activity.calculateCalories(activity.getActivityType(), activity.getDuration(), userWeight));
        activity.setCreatedAt(LocalDateTime.now());
        return activityRepository.save(activity);
    }

    public Optional<Activity> getActivityById(String id) {
        return activityRepository.findById(id);
    }

    public List<Activity> getUserActivities(String userId) {
        return activityRepository.findByUserId(userId);
    }

    public List<Activity> getActivitiesByDateRange(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        return activityRepository.findByUserIdAndActivityDateBetween(userId, startDate, endDate);
    }

    public List<Activity> getActivitiesByType(String userId, String activityType) {
        return activityRepository.findByUserIdAndActivityType(userId, activityType);
    }

    public Activity updateActivity(String id, Activity activity) {
        Optional<Activity> existingActivity = activityRepository.findById(id);
        if (existingActivity.isPresent()) {
            Activity activityToUpdate = existingActivity.get();
            if (activity.getDuration() > 0) activityToUpdate.setDuration(activity.getDuration());
            if (activity.getDistance() >= 0) activityToUpdate.setDistance(activity.getDistance());
            if (activity.getCaloriesBurned() >= 0) activityToUpdate.setCaloriesBurned(activity.getCaloriesBurned());
            if (activity.getNotes() != null) activityToUpdate.setNotes(activity.getNotes());
            return activityRepository.save(activityToUpdate);
        }
        throw new RuntimeException("Activity not found");
    }

    public void deleteActivity(String id) {
        activityRepository.deleteById(id);
    }

    public double getTotalCaloriesBurned(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Activity> activities = getActivitiesByDateRange(userId, startDate, endDate);
        return activities.stream().mapToDouble(Activity::getCaloriesBurned).sum();
    }

    public double getTotalDistance(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Activity> activities = getActivitiesByDateRange(userId, startDate, endDate);
        return activities.stream().mapToDouble(Activity::getDistance).sum();
    }
}
