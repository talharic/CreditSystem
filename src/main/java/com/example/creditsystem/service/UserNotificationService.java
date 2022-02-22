package com.example.creditsystem.service;

import com.example.creditsystem.entity.User;

public interface UserNotificationService {
    void notifyUser(User user, String message);
}
