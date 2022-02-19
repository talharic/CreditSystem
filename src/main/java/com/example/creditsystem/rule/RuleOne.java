package com.example.creditsystem.rule;

import com.example.creditsystem.entity.CreditApplication;

public class RuleOne implements CreditCalculationRule{
    @Override
    public double calculate(CreditApplication creditApplication) {
        double amount = 0;
        long creditScore = creditApplication.getCreditScore();
        double monthlyIncome = creditApplication.getMonthlyIncome();

        if(creditScore >=500 && creditScore<1_000 && monthlyIncome < 5_000){
            amount = 10_000;
        }
        return amount;
    }
}
