package com.fitnesstracker.service;

import com.fitnesstracker.model.Exercise;
import com.fitnesstracker.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Optional<Exercise> getExerciseById(String id) {
        return exerciseRepository.findById(id);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public List<Exercise> getExercisesByDifficulty(String difficulty) {
        return exerciseRepository.findByDifficulty(difficulty);
    }

    public List<Exercise> getExercisesByTargetMuscles(String targetMuscles) {
        return exerciseRepository.findByTargetMusclesContaining(targetMuscles);
    }

    public Exercise updateExercise(String id, Exercise exercise) {
        Optional<Exercise> existingExercise = exerciseRepository.findById(id);
        if (existingExercise.isPresent()) {
            Exercise exerciseToUpdate = existingExercise.get();
            if (exercise.getName() != null) exerciseToUpdate.setName(exercise.getName());
            if (exercise.getDescription() != null) exerciseToUpdate.setDescription(exercise.getDescription());
            if (exercise.getTargetMuscles() != null) exerciseToUpdate.setTargetMuscles(exercise.getTargetMuscles());
            if (exercise.getDifficulty() != null) exerciseToUpdate.setDifficulty(exercise.getDifficulty());
            if (exercise.getInstructions() != null) exerciseToUpdate.setInstructions(exercise.getInstructions());
            if (exercise.getImageUrl() != null) exerciseToUpdate.setImageUrl(exercise.getImageUrl());
            return exerciseRepository.save(exerciseToUpdate);
        }
        throw new RuntimeException("Exercise not found");
    }

    public void deleteExercise(String id) {
        exerciseRepository.deleteById(id);
    }

    public void initializeDefaultExercises() {
        if (exerciseRepository.count() == 0) {
            exerciseRepository.save(new Exercise("Push Ups", "Classic bodyweight exercise", "Chest, Shoulders, Triceps", "Beginner"));
            exerciseRepository.save(new Exercise("Squats", "Lower body strength exercise", "Legs, Glutes", "Beginner"));
            exerciseRepository.save(new Exercise("Plank", "Core stability exercise", "Core, Abs", "Beginner"));
            exerciseRepository.save(new Exercise("Bench Press", "Chest strength exercise", "Chest, Shoulders, Triceps", "Intermediate"));
            exerciseRepository.save(new Exercise("Deadlift", "Full body strength exercise", "Back, Legs, Core", "Intermediate"));
            exerciseRepository.save(new Exercise("Pull Ups", "Upper body strength", "Back, Shoulders, Biceps", "Intermediate"));
        }
    }
}
