package com.example.creditsystem.service;

public interface CreditScoreService {
    long calculateCreditScore(Double monthlyIncome, String nationalIdNumber);
}
