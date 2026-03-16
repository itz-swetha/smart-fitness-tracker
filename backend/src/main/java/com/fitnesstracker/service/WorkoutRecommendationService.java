package com.fitnesstracker.service;

import com.fitnesstracker.model.WorkoutRecommendation;
import com.fitnesstracker.repository.ExerciseRepository;
import com.fitnesstracker.repository.WorkoutRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
public class WorkoutRecommendationService {

    @Autowired
    private WorkoutRecommendationRepository workoutRecommendationRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public WorkoutRecommendation generateRecommendation(String userId, String fitnessGoal) {
        WorkoutRecommendation recommendation = new WorkoutRecommendation(userId, fitnessGoal);

        switch (fitnessGoal.toLowerCase()) {
            case "weight loss":
                recommendation.setRecommendedActivities(Arrays.asList("Running", "Swimming", "Cycling"));
                recommendation.setRecommendationReason("High intensity cardio activities are ideal for weight loss");
                break;
            case "muscle gain":
                recommendation.setRecommendedActivities(Arrays.asList("Strength Training", "Resistance Exercises"));
                recommendation.setRecommendationReason("Focus on strength training with progressive overload");
                break;
            case "endurance":
                recommendation.setRecommendedActivities(Arrays.asList("Running", "Swimming", "Yoga"));
                recommendation.setRecommendationReason("Build cardiovascular endurance through consistent cardio");
                break;
            case "flexibility":
                recommendation.setRecommendedActivities(Arrays.asList("Yoga", "Stretching", "Pilates"));
                recommendation.setRecommendationReason("Focus on flexibility and mobility work");
                break;
            default:
                recommendation.setRecommendedActivities(Arrays.asList("Balanced Cardio", "Strength Training", "Yoga"));
                recommendation.setRecommendationReason("Balanced fitness routine for overall health");
        }

        recommendation.setCreatedAt(LocalDateTime.now());
        return workoutRecommendationRepository.save(recommendation);
    }

    public Optional<WorkoutRecommendation> getRecommendationForUser(String userId) {
        return workoutRecommendationRepository.findByUserId(userId);
    }

    public WorkoutRecommendation updateRecommendation(String id, WorkoutRecommendation recommendation) {
        Optional<WorkoutRecommendation> existingRec = workoutRecommendationRepository.findById(id);
        if (existingRec.isPresent()) {
            WorkoutRecommendation recToUpdate = existingRec.get();
            if (recommendation.getFitnessGoal() != null) recToUpdate.setFitnessGoal(recommendation.getFitnessGoal());
            if (recommendation.getRecommendedExerciseIds() != null) recToUpdate.setRecommendedExerciseIds(recommendation.getRecommendedExerciseIds());
            if (recommendation.getRecommendedActivities() != null) recToUpdate.setRecommendedActivities(recommendation.getRecommendedActivities());
            return workoutRecommendationRepository.save(recToUpdate);
        }
        throw new RuntimeException("Recommendation not found");
    }

    public void deleteRecommendation(String id) {
        workoutRecommendationRepository.deleteById(id);
    }
}
