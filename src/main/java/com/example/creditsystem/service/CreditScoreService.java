package com.example.creditsystem.service;

public interface CreditScoreService {
    Long calculateCreditScore(Double monthlyIncome, String nationalIdNumber);
}
