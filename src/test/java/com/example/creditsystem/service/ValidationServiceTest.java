package com.example.creditsystem.service;

import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.exception.CreditApplicationNotFoundException;
import com.example.creditsystem.exception.NationalIdNumberNotValidException;
import com.example.creditsystem.exception.UserAlreadyExistException;
import com.example.creditsystem.exception.UserNotFoundException;
import com.example.creditsystem.service.impl.ValidationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ValidationServiceTest {

    @InjectMocks
    ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = mock(ValidationServiceImpl.class);
    }

    @Test
    void shouldValidateUser() {
        Optional<User> optionalUser = Optional.of(new User());
        User user = new User();

        when(validationService.validateUser(optionalUser)).thenReturn(user);
    }

    @Test
    void shouldNotValidateUser() {
        Optional<User> optionalUser = Optional.empty();
        when(validationService.validateUser(optionalUser)).thenThrow(new UserNotFoundException(""));
    }

    @Test
    void shouldValidateCreditApplication() {
        Optional<CreditApplication> optionalCreditApplication = Optional.of(new CreditApplication());
        CreditApplication creditApplication = new CreditApplication();

        when(validationService.validateCreditApplication(optionalCreditApplication)).thenReturn(creditApplication);
    }

    @Test
    void shouldNotValidateCreditApplication() {
        Optional<CreditApplication> creditApplication = Optional.empty();

        when(validationService.validateCreditApplication(creditApplication)).thenThrow(new CreditApplicationNotFoundException(""));
    }

    @Test
    void shouldValidatePhoneNumber() {
        String phone = "5395893797";
        when(validationService.validatePhoneNumber(phone)).thenReturn(true);

    }

    @Test
    void shouldNotValidatePhoneNumber() {
        String phone = "abx4-?797";
        when(validationService.validatePhoneNumber(phone)).thenThrow(new UserNotFoundException(""));
    }


    @Test
    void shouldValidateNationalIdNumber() {
        String nationalId = "12585497585";
        when(validationService.validateNationalIdNumber(nationalId)).thenReturn(true);
    }

    @Test
    void shouldNotValidateNationalIdNumber() {
        String nationalIdNumber = "abx4-?585";
        when(validationService.validateNationalIdNumber(nationalIdNumber)).thenThrow(new NationalIdNumberNotValidException(""));
    }


    @Test
    void shouldValidateUserNotExist() {
        Optional<User> optionalUser = Optional.of(new User());
        when(validationService.validateUserNotExist(optionalUser)).thenThrow(new UserAlreadyExistException(""));
    }

    @Test
    void shouldNotValidateUserNotExist() {
        Optional<User> optionalUser = Optional.empty();
        when(validationService.validateUserNotExist(optionalUser)).thenThrow(new UserAlreadyExistException(""));
    }

}
