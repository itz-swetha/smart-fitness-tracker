package com.fitnesstracker.service;

import com.fitnesstracker.model.WorkoutRoutine;
import com.fitnesstracker.repository.WorkoutRoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutRoutineService {

    @Autowired
    private WorkoutRoutineRepository workoutRoutineRepository;

    public WorkoutRoutine createWorkoutRoutine(WorkoutRoutine workoutRoutine) {
        workoutRoutine.setCreatedAt(LocalDateTime.now());
        workoutRoutine.setUpdatedAt(LocalDateTime.now());
        return workoutRoutineRepository.save(workoutRoutine);
    }

    public Optional<WorkoutRoutine> getWorkoutRoutineById(String id) {
        return workoutRoutineRepository.findById(id);
    }

    public List<WorkoutRoutine> getUserWorkoutRoutines(String userId) {
        return workoutRoutineRepository.findByUserId(userId);
    }

    public WorkoutRoutine updateWorkoutRoutine(String id, WorkoutRoutine workoutRoutine) {
        Optional<WorkoutRoutine> existingRoutine = workoutRoutineRepository.findById(id);
        if (existingRoutine.isPresent()) {
            WorkoutRoutine routineToUpdate = existingRoutine.get();
            if (workoutRoutine.getName() != null) routineToUpdate.setName(workoutRoutine.getName());
            if (workoutRoutine.getDescription() != null) routineToUpdate.setDescription(workoutRoutine.getDescription());
            if (workoutRoutine.getSets() != null) routineToUpdate.setSets(workoutRoutine.getSets());
            routineToUpdate.setUpdatedAt(LocalDateTime.now());
            return workoutRoutineRepository.save(routineToUpdate);
        }
        throw new RuntimeException("Workout Routine not found");
    }

    public void deleteWorkoutRoutine(String id) {
        workoutRoutineRepository.deleteById(id);
    }
}
