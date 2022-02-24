package com.example.creditsystem.service.impl;

import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.exception.CreditApplicationNotFoundException;
import com.example.creditsystem.exception.NationalIdNumberNotValidException;
import com.example.creditsystem.exception.UserAlreadyExistException;
import com.example.creditsystem.exception.UserNotFoundException;
import com.example.creditsystem.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public Boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public Boolean validateNationalIdNumber(String nationalIdNumber) {
        Pattern pattern = Pattern.compile("^\\d{11}$");
        Matcher matcher = pattern.matcher(nationalIdNumber);
        if (!matcher.matches())
            throw new NationalIdNumberNotValidException("National Id is not valid");
        return matcher.matches();
    }

    @Override
    public Boolean validateUserNotExist(Optional<User> optionalUser) {
        if (optionalUser.isPresent())
            throw new UserAlreadyExistException("User has already exist.");
        return Boolean.TRUE;
    }
}
