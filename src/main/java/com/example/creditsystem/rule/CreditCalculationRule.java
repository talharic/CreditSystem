package com.example.creditsystem.rule;

import com.example.creditsystem.entity.CreditApplication;

public interface CreditCalculationRule {
    double calculate(CreditApplication creditApplication);

}
