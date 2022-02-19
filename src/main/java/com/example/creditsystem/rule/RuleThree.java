package com.example.creditsystem.rule;

import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.util.Constant;

public class RuleThree implements CreditCalculationRule {
    @Override
    public double calculate(CreditApplication creditApplication) {
        double amount = 0;
        long creditScore = creditApplication.getCreditScore();
        double monthlyIncome = creditApplication.getMonthlyIncome();

        if (creditScore >= 1_000) {
            amount = (monthlyIncome * Constant.CREDIT_LIMIT_MULTIPLIER);
        }
        return amount;
    }
}
