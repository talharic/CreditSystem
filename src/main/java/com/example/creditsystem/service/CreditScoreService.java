package com.example.creditsystem.service;

import org.springframework.stereotype.Service;

@Service
public class CreditScoreService {
    public Long calculateCreditScore(Double monthlyIncome, String nationalIdNumber) {
        return 1000L;
    }
}
