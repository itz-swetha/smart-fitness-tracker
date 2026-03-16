package com.fitnesstracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private double height; // in cm
    private double weight; // in kg
    private String fitnessGoal; // e.g., "Weight Loss", "Muscle Gain", "Endurance"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public double calculateBMI() {
        return weight / ((height / 100) * (height / 100));
    }
}
