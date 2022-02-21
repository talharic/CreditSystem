package com.example.creditsystem.service.impl;

import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.exception.CreditApplicationNotFoundException;
import com.example.creditsystem.exception.UserNotFoundException;
import com.example.creditsystem.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public User validateUser(Optional<User> user) {
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        return user.get();
    }

    @Override
    public CreditApplication validateCreditApplication(Optional<CreditApplication> creditApplication) {
        if (creditApplication.isEmpty()) {
            throw new CreditApplicationNotFoundException("Credit Application has been found!");
        }
        return creditApplication.get();
    }
}
