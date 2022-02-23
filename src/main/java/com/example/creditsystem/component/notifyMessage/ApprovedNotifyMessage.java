package com.example.creditsystem.component.notifyMessage;

import com.example.creditsystem.enums.CreditApplicationResult;
import org.springframework.stereotype.Component;

@Component
public class ApprovedNotifyMessage implements NotifyMessage {

    @Override
    public String getType() {
        return CreditApplicationResult.APPROVED.getResult();
    }

    @Override
    public String getMessage(String fullName, double limit) {
        StringBuilder notifyMessage = new StringBuilder();
        notifyMessage.append("Dear ").append(fullName).append(", ")
                .append("your credit application has been ")
                .append(this.getType()).append(". ")
                .append("Your credit limit: ")
                .append(limit)
                .append(". ")
                .append("Thank you for your application.");
        return notifyMessage.toString();
    }

}