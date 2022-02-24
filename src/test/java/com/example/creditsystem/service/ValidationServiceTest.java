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

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    @InjectMocks
    ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationServiceImpl();
    }

    @Test
    void shouldValidateUser() {
        User user = new User();
        Optional<User> optionalUser = Optional.of(user);
        assertEquals(user, validationService.validateUser(optionalUser));
    }

    @Test
    void shouldNotValidateUser() {
        Optional<User> optionalUser = Optional.empty();
        assertThrows(UserNotFoundException.class,()-> validationService.validateUser(optionalUser));
    }

    @Test
    void shouldValidateCreditApplication() {
        CreditApplication creditApplication = new CreditApplication();
        Optional<CreditApplication> optionalCreditApplication = Optional.of(creditApplication);
        assertEquals(creditApplication, validationService.validateCreditApplication(optionalCreditApplication));
    }

    @Test
    void shouldNotValidateCreditApplication() {
        Optional<CreditApplication> creditApplication = Optional.empty();
        assertThrows(CreditApplicationNotFoundException.class,()-> validationService.validateCreditApplication(creditApplication));
    }

    @Test
    void shouldValidatePhoneNumber() {
        String phone = "5394293797";
        assertEquals(Boolean.TRUE, validationService.validatePhoneNumber(phone));

    }

    @Test
    void shouldNotValidatePhoneNumber() {
        String phone = "abx4-?797";
        assertEquals(Boolean.FALSE,validationService.validatePhoneNumber(phone));
    }


    @Test
    void shouldValidateNationalIdNumber() {
        String nationalId = "12585496572";
        assertEquals(Boolean.TRUE, validationService.validateNationalIdNumber(nationalId));

    }

    @Test
    void shouldNotValidateNationalIdNumber() {
        String nationalIdNumber = "abx4-?572";
        assertThrows(NationalIdNumberNotValidException.class,()-> validationService.validateNationalIdNumber(nationalIdNumber));
    }


    @Test
    void shouldValidateUserNotExist() {
        Optional<User> optionalUser = Optional.empty();
        assertEquals(Boolean.TRUE, validationService.validateUserNotExist(optionalUser));
    }

    @Test
    void shouldNotValidateUserNotExist() {
        Optional<User> optionalUser = Optional.of(new User());
        assertThrows(UserAlreadyExistException.class,()-> validationService.validateUserNotExist(optionalUser));
    }

}
