package com.example.creditsystem.service;

import com.example.creditsystem.entity.User;
import com.example.creditsystem.service.impl.SmsNotificationService;
import com.example.creditsystem.service.impl.ValidationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

class UserNotificationServiceTest {

    @Mock
    ValidationService validationService;

    @InjectMocks
    UserNotificationService userNotificationService;

    @BeforeEach
    void setUp() {
        validationService = mock(ValidationServiceImpl.class);
        userNotificationService = new SmsNotificationService(validationService);
    }

    @Test
    void notifyUser() {
        User user = new User();
        String smsMessage = "Dummy message";
        user.setPhone("5395893797");
        user.setNationalIdNumber("395893797");

        userNotificationService.notifyUser(user, smsMessage);

        verify(validationService, times(1)).validatePhoneNumber(user.getPhone());

        when(validationService.validatePhoneNumber(user.getPhone())).thenReturn(true);
    }

    @Test
    void shouldNotNotifyUser() {
        User user = new User();
        String smsMessage = "Dummy message";
        user.setPhone("53?-asda797");
        user.setNationalIdNumber("12585497585");
        userNotificationService.notifyUser(user, smsMessage);

        verify(validationService, times(1)).validatePhoneNumber(user.getPhone());

        when(validationService.validatePhoneNumber(user.getPhone())).thenReturn(false);
    }

}
