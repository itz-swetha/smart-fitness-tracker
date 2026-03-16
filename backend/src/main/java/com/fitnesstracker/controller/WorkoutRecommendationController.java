package com.fitnesstracker.controller;

import com.fitnesstracker.model.WorkoutRecommendation;
import com.fitnesstracker.service.WorkoutRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/recommendations")
@CrossOrigin(origins = "*")
public class WorkoutRecommendationController {

    @Autowired
    private WorkoutRecommendationService workoutRecommendationService;

    @PostMapping("/{userId}/{fitnessGoal}")
    public ResponseEntity<?> generateRecommendation(@PathVariable String userId, @PathVariable String fitnessGoal) {
        try {
            WorkoutRecommendation recommendation = workoutRecommendationService.generateRecommendation(userId, fitnessGoal);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Recommendation generated successfully");
            response.put("recommendation", recommendation);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRecommendationForUser(@PathVariable String userId) {
        Optional<WorkoutRecommendation> recommendation = workoutRecommendationService.getRecommendationForUser(userId);
        if (recommendation.isPresent()) {
            return ResponseEntity.ok(recommendation.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
            put("error", "No recommendation found for user");
        }});
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecommendation(@PathVariable String id, @RequestBody WorkoutRecommendation recommendation) {
        try {
            WorkoutRecommendation updatedRec = workoutRecommendationService.updateRecommendation(id, recommendation);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Recommendation updated successfully");
            response.put("recommendation", updatedRec);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecommendation(@PathVariable String id) {
        try {
            workoutRecommendationService.deleteRecommendation(id);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Recommendation deleted successfully");
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("error", "Recommendation not found");
            }});
        }
    }
}
