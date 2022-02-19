package com.example.creditsystem.service;

import com.example.creditsystem.entity.CreditApplication;
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

    public CreditApplication validateCreditApplication(Optional<CreditApplication> creditApplication) {
        if (creditApplication.isPresent()) {
            return creditApplication.get();
        } else
            throw new UserNotFoundException("Credit Application not found!");
    }
}
