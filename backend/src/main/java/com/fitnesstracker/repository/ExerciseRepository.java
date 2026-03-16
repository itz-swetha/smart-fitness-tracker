package com.fitnesstracker.repository;

import com.fitnesstracker.model.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExerciseRepository extends MongoRepository<Exercise, String> {
    List<Exercise> findByDifficulty(String difficulty);
    List<Exercise> findByTargetMusclesContaining(String targetMuscles);
}
