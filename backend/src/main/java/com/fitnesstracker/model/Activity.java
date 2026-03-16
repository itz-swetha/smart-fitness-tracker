package com.fitnesstracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    private String id;
    private String userId;
    private String activityType; // "Running", "Swimming", "Yoga"
    private double duration; // in minutes
    private double distance; // in km
    private double caloriesBurned;
    private LocalDateTime activityDate;
    private String notes;
    private LocalDateTime createdAt;

    public double calculateCalories(String type, double duration, double weight) {
        // Approximate calorie burn rates (kcal/min per kg body weight)
        double burnRate = 0;
        switch (type.toLowerCase()) {
            case "running":
                burnRate = 0.16; // 9.6 kcal/min for 60kg person
                break;
            case "swimming":
                burnRate = 0.11; // 6.6 kcal/min for 60kg person
                break;
            case "yoga":
                burnRate = 0.03; // 1.8 kcal/min for 60kg person
                break;
            default:
                burnRate = 0.05;
        }
        return duration * burnRate * weight;
    }
}
