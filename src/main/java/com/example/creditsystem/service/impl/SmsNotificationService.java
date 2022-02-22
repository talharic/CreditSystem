package com.example.creditsystem.service.impl;

import com.example.creditsystem.entity.User;
import com.example.creditsystem.service.UserNotificationService;
import com.example.creditsystem.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsNotificationService implements UserNotificationService {

    private final ValidationService validationService;

    public SmsNotificationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public void notifyUser(User user, String message) {
        if (validationService.validatePhoneNumber(user.getPhone())) {
            log.info(message);
            log.info("SMS message {} sent to the user {}", message, user.getNationalIdNumber());
        } else {
            log.warn("User phone number is missing or wrong format to send sms notification");
        }
    }
}
