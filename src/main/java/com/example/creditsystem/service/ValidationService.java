package com.example.creditsystem.service;

import com.example.creditsystem.entity.User;
import com.example.creditsystem.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationService {

    public User validateUser(Optional<User> user) {
        if (user.isPresent()) {
            return user.get();
        } else
            throw new UserNotFoundException("User not found!");
    }
}
