package com.example.creditsystem.dto;

import com.example.creditsystem.enums.CreditApplicationResult;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CreditApplicationResponseDto implements Serializable {
    private final String nationalIdNumber;
    private final String name;
    private final String surname;
    private final String phone;
    private final Double monthlyIncome;
    private final Double creditLimitAmount;
    private final LocalDateTime applicationDate;
    private final CreditApplicationResult creditApplicationResult;
}
