package com.example.creditsystem.service;

import com.example.creditsystem.entity.User;
import com.example.creditsystem.service.impl.SmsNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserNotificationServiceTest {

    @Mock
    ValidationService validationService;

    @InjectMocks
    SmsNotificationService userNotificationService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void notifyUser() {
        User user = new User();
        String smsMessage = "Dummy message";
        user.setPhone("5395893797");
        user.setNationalIdNumber("5395893797");

        when(validationService.validatePhoneNumber(user.getPhone())).thenReturn(true);

        userNotificationService.notifyUser(user, smsMessage);

        verify(validationService, times(1)).validatePhoneNumber(user.getPhone());

    }

    @Test
    void shouldNotNotifyUser() {
        User user = new User();
        String smsMessage = "Dummy message";
        user.setPhone("53?-asda797");
        user.setNationalIdNumber("5395893797");

        when(validationService.validatePhoneNumber(user.getPhone())).thenReturn(false);

        userNotificationService.notifyUser(user, smsMessage);

        verify(validationService, times(1)).validatePhoneNumber(user.getPhone());

    }

}
