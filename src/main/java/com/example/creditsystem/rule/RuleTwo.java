package com.example.creditsystem.rule;

import com.example.creditsystem.dto.CreditCalculationDto;

public class RuleTwo implements CreditCalculationRule{
    @Override
    public double calculate(CreditCalculationDto creditCalculationDto) {
        double amount = 0;
        long creditScore = creditCalculationDto.getCreditScore();
        double monthlyIncome = creditCalculationDto.getMonthlyIncome();

        if(creditScore >=500 && creditScore<1_000 && monthlyIncome > 5_000){
            amount = 20_000;
        }
        return amount;
    }
}