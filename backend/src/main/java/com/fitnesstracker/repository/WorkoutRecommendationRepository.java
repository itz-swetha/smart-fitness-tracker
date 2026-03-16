package com.fitnesstracker.repository;

import com.fitnesstracker.model.WorkoutRecommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WorkoutRecommendationRepository extends MongoRepository<WorkoutRecommendation, String> {
    Optional<WorkoutRecommendation> findByUserId(String userId);
}
