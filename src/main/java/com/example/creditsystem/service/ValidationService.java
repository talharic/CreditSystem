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
        if (user.isPresent()) {
            return user.get();
        } else
            throw new UserNotFoundException("User not found!");
    }

    public List<CreditApplication> validateCreditApplicationList(Optional<List<CreditApplication>> creditApplicationList) {
        if (creditApplicationList.isPresent()) {
            return creditApplicationList.get();
        } else
            throw new CreditApplicationNotFoundException("No Credit Application has been found!");
    }
}
