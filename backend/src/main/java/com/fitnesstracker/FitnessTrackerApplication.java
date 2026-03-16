package com.fitnesstracker;

import com.fitnesstracker.service.ExerciseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FitnessTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitnessTrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeData(ExerciseService exerciseService) {
        return args -> {
            exerciseService.initializeDefaultExercises();
        };
    }
}
