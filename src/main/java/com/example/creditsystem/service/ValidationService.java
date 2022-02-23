package com.example.creditsystem.service;

import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;

import java.util.Optional;

public interface ValidationService {
    User validateUser(Optional<User> user);

    CreditApplication validateCreditApplication(Optional<CreditApplication> creditApplication);

    Boolean validatePhoneNumber(String phoneNumber);

    Boolean validateNationalIdNumber(String nationalIdNumber);

    Boolean validateUserNotExist(Optional<User> byNationalIdNumber);
}
