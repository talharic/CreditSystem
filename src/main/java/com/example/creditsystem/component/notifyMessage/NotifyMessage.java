package com.example.creditsystem.component.notifyMessage;

public interface NotifyMessage {
    String getType();
    String getMessage(String fullName, double limit);
}
