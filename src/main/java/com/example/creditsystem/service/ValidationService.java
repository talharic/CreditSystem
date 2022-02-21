package com.example.creditsystem.service;

import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.exception.CreditApplicationNotFoundException;
import com.example.creditsystem.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ValidationService {

    public User validateUser(Optional<User> user) {
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        return user.get();
    }

    public List<CreditApplication> validateCreditApplicationList(Optional<List<CreditApplication>> creditApplicationList) {
        if (creditApplicationList.isEmpty()) {
            throw new CreditApplicationNotFoundException("No Credit Application has been found!");
        }
        return creditApplicationList.get();
    }
}
