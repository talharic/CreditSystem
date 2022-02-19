package com.example.creditsystem.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreditCalculationDto {
    private final double monthlyIncome;
    private final long creditScore;
}
