package com.fitnesstracker.controller;

import com.fitnesstracker.model.WorkoutRoutine;
import com.fitnesstracker.service.WorkoutRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/workout-routines")
@CrossOrigin(origins = "*")
public class WorkoutRoutineController {

    @Autowired
    private WorkoutRoutineService workoutRoutineService;

    @PostMapping
    public ResponseEntity<?> createWorkoutRoutine(@RequestBody WorkoutRoutine workoutRoutine) {
        try {
            WorkoutRoutine createdRoutine = workoutRoutineService.createWorkoutRoutine(workoutRoutine);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Workout routine created successfully");
            response.put("routine", createdRoutine);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkoutRoutineById(@PathVariable String id) {
        Optional<WorkoutRoutine> routine = workoutRoutineService.getWorkoutRoutineById(id);
        if (routine.isPresent()) {
            return ResponseEntity.ok(routine.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
            put("error", "Workout routine not found");
        }});
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutRoutine>> getUserWorkoutRoutines(@PathVariable String userId) {
        List<WorkoutRoutine> routines = workoutRoutineService.getUserWorkoutRoutines(userId);
        return ResponseEntity.ok(routines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkoutRoutine(@PathVariable String id, @RequestBody WorkoutRoutine workoutRoutine) {
        try {
            WorkoutRoutine updatedRoutine = workoutRoutineService.updateWorkoutRoutine(id, workoutRoutine);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Workout routine updated successfully");
            response.put("routine", updatedRoutine);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkoutRoutine(@PathVariable String id) {
        try {
            workoutRoutineService.deleteWorkoutRoutine(id);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Workout routine deleted successfully");
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("error", "Workout routine not found");
            }});
        }
    }
}
