package com.example.creditsystem.rule;

public class RuleTwo implements CreditCalculationRule{
    @Override
    public double calculate( long creditScore, double monthlyIncome) {
        double amount = 0;

        if(creditScore >=500 && creditScore<1_000 && monthlyIncome > 5_000){
            amount = 20_000;
        }
        return amount;
    }
}
