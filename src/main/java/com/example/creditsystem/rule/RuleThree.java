package com.example.creditsystem.rule;

import com.example.creditsystem.util.Constant;

public class RuleThree implements CreditCalculationRule {
    @Override
    public double calculate( long creditScore, double monthlyIncome) {
        double amount = 0;

        if (creditScore >= 1_000) {
            amount = (monthlyIncome * Constant.CREDIT_LIMIT_MULTIPLIER);
        }
        return amount;
    }
}
