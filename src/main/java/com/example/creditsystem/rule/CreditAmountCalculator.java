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

    public double getCreditLimitAmount(Long creditScore, Double monthlyIncome) {
        double creditAmount = 0.0;
        if (creditScore == null || monthlyIncome == null)
            return creditAmount;
        for (CreditCalculationRule calculationRule : calculationRules) {
            creditAmount += calculationRule.calculate(creditScore, monthlyIncome);
        }
        log.info("creditAmount: " + creditAmount);
        return creditAmount;
    }
}
