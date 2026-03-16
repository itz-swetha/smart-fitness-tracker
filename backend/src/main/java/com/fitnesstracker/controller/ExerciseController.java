package com.fitnesstracker.controller;

import com.fitnesstracker.model.Exercise;
import com.fitnesstracker.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/exercises")
@CrossOrigin(origins = "*")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise) {
        try {
            Exercise createdExercise = exerciseService.createExercise(exercise);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Exercise created successfully");
            response.put("exercise", createdExercise);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExerciseById(@PathVariable String id) {
        Optional<Exercise> exercise = exerciseService.getExerciseById(id);
        if (exercise.isPresent()) {
            return ResponseEntity.ok(exercise.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
            put("error", "Exercise not found");
        }});
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Exercise>> getExercisesByDifficulty(@PathVariable String difficulty) {
        List<Exercise> exercises = exerciseService.getExercisesByDifficulty(difficulty);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/muscles/{targetMuscles}")
    public ResponseEntity<List<Exercise>> getExercisesByTargetMuscles(@PathVariable String targetMuscles) {
        List<Exercise> exercises = exerciseService.getExercisesByTargetMuscles(targetMuscles);
        return ResponseEntity.ok(exercises);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable String id, @RequestBody Exercise exercise) {
        try {
            Exercise updatedExercise = exerciseService.updateExercise(id, exercise);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Exercise updated successfully");
            response.put("exercise", updatedExercise);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable String id) {
        try {
            exerciseService.deleteExercise(id);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Exercise deleted successfully");
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("error", "Exercise not found");
            }});
        }
    }

    @PostMapping("/initialize")
    public ResponseEntity<?> initializeExercises() {
        try {
            exerciseService.initializeDefaultExercises();
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Default exercises initialized successfully");
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }
}
