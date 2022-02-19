package com.example.creditsystem.rule;

import com.example.creditsystem.dto.CreditCalculationDto;

public interface CreditCalculationRule {
    double calculate(CreditCalculationDto creditCalculationDto);
}
