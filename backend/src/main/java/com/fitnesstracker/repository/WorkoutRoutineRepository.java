package com.fitnesstracker.repository;

import com.fitnesstracker.model.WorkoutRoutine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkoutRoutineRepository extends MongoRepository<WorkoutRoutine, String> {
    List<WorkoutRoutine> findByUserId(String userId);
}
