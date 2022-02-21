package com.example.creditsystem.rule;

public interface CreditCalculationRule {
    double calculate(long creditScore, double monthlyIncome);
}
