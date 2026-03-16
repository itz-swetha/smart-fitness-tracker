package com.fitnesstracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    private String id;
    private String name;
    private String description;
    private String targetMuscles;
    private String difficulty; // "Beginner", "Intermediate", "Advanced"
    private String instructions;
    private String imageUrl;

    public Exercise(String name, String description, String targetMuscles, String difficulty) {
        this.name = name;
        this.description = description;
        this.targetMuscles = targetMuscles;
        this.difficulty = difficulty;
    }
}
