package com.fitnesstracker.controller;

import com.fitnesstracker.model.Activity;
import com.fitnesstracker.model.User;
import com.fitnesstracker.service.ActivityService;
import com.fitnesstracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/activities")
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createActivity(@RequestBody Activity activity) {
        try {
            Optional<User> user = userService.getUserById(activity.getUserId());
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                    put("error", "User not found");
                }});
            }
            Activity createdActivity = activityService.createActivity(activity, user.get().getWeight());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Activity created successfully");
            response.put("activity", createdActivity);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActivityById(@PathVariable String id) {
        Optional<Activity> activity = activityService.getActivityById(id);
        if (activity.isPresent()) {
            return ResponseEntity.ok(activity.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
            put("error", "Activity not found");
        }});
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Activity>> getUserActivities(@PathVariable String userId) {
        List<Activity> activities = activityService.getUserActivities(userId);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/user/{userId}/type/{activityType}")
    public ResponseEntity<List<Activity>> getActivitiesByType(@PathVariable String userId, @PathVariable String activityType) {
        List<Activity> activities = activityService.getActivitiesByType(userId, activityType);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/user/{userId}/daterange")
    public ResponseEntity<?> getActivitiesByDateRange(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Activity> activities = activityService.getActivitiesByDateRange(userId, startDate, endDate);
        Map<String, Object> response = new HashMap<>();
        response.put("activities", activities);
        response.put("count", activities.size());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivity(@PathVariable String id, @RequestBody Activity activity) {
        try {
            Activity updatedActivity = activityService.updateActivity(id, activity);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Activity updated successfully");
            response.put("activity", updatedActivity);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable String id) {
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Activity deleted successfully");
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("error", "Activity not found");
            }});
        }
    }

    @GetMapping("/user/{userId}/stats")
    public ResponseEntity<?> getUserActivityStats(
            @PathVariable String userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        
        LocalDateTime start = startDate != null ? startDate : LocalDateTime.now().minusDays(7);
        LocalDateTime end = endDate != null ? endDate : LocalDateTime.now();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalCalories", activityService.getTotalCaloriesBurned(userId, start, end));
        stats.put("totalDistance", activityService.getTotalDistance(userId, start, end));
        stats.put("activities", activityService.getActivitiesByDateRange(userId, start, end));
        stats.put("startDate", start);
        stats.put("endDate", end);
        
        return ResponseEntity.ok(stats);
    }
}
