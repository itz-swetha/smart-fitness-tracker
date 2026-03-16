package com.fitnesstracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "workout_routines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRoutine {
    @Id
    private String id;
    private String userId;
    private String name;
    private String description;
    private List<WorkoutSet> sets;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkoutSet {
        private String exerciseId;
        private String exerciseName;
        private int reps;
        private double weight; // in kg
        private int sets;
        private int restSeconds;
    }
}
