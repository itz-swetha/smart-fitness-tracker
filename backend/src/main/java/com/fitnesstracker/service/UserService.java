package com.fitnesstracker.service;

import com.fitnesstracker.model.User;
import com.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(String id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            if (user.getFirstName() != null) userToUpdate.setFirstName(user.getFirstName());
            if (user.getLastName() != null) userToUpdate.setLastName(user.getLastName());
            if (user.getAge() > 0) userToUpdate.setAge(user.getAge());
            if (user.getHeight() > 0) userToUpdate.setHeight(user.getHeight());
            if (user.getWeight() > 0) userToUpdate.setWeight(user.getWeight());
            if (user.getFitnessGoal() != null) userToUpdate.setFitnessGoal(user.getFitnessGoal());
            userToUpdate.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(userToUpdate);
        }
        throw new RuntimeException("User not found");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
