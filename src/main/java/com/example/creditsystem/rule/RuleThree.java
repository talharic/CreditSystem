package com.example.creditsystem.rule;

import com.example.creditsystem.dto.CreditCalculationDto;
import com.example.creditsystem.util.Constant;

public class RuleThree implements CreditCalculationRule {
    @Override
    public double calculate(CreditCalculationDto creditCalculationDto) {
        double amount = 0;
        long creditScore = creditCalculationDto.getCreditScore();
        double monthlyIncome = creditCalculationDto.getMonthlyIncome();

        if (creditScore >= 1_000) {
            amount = (monthlyIncome * Constant.CREDIT_LIMIT_MULTIPLIER);
        }
        return amount;
    }
}
