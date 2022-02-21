package com.example.creditsystem.rule;

import com.example.creditsystem.entity.CreditApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CreditAmountCalculator {
    List<CreditCalculationRule> calculationRules = new ArrayList<>();

    public CreditAmountCalculator() {
        this.calculationRules.add(new RuleOne());
        this.calculationRules.add(new RuleTwo());
        this.calculationRules.add(new RuleThree());
    }

    public double getCreditLimitAmount(CreditApplication creditApplication) {
        double creditAmount = 0.0;
        long creditScore = creditApplication.getCreditScore() == null ? 0 : creditApplication.getCreditScore();
        double monthlyIncome = creditApplication.getMonthlyIncome() == null ? 0 : creditApplication.getMonthlyIncome();
        for (CreditCalculationRule calculationRule : calculationRules) {
            creditAmount += calculationRule.calculate(creditScore, monthlyIncome);
        }
        log.info("creditAmount: " + creditAmount);
        return creditAmount;
    }
}
