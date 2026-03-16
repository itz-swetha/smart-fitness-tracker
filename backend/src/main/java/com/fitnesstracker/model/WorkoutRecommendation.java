package com.fitnesstracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "workout_recommendations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecommendation {
    @Id
    private String id;
    private String userId;
    private String fitnessGoal;
    private List<String> recommendedExerciseIds;
    private List<String> recommendedActivities;
    private String recommendationReason;
    private LocalDateTime createdAt;

    public WorkoutRecommendation(String userId, String fitnessGoal) {
        this.userId = userId;
        this.fitnessGoal = fitnessGoal;
        this.createdAt = LocalDateTime.now();
    }
}
