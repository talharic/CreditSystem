package com.example.creditsystem.dto;

import com.example.creditsystem.enums.CreditApplicationResult;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CreditApplicationResponseDto implements Serializable {
    private final String userNationalIdNumber;
    private final String userName;
    private final String userSurname;
    private final String userPhone;
    private final Double monthlyIncome;
    private final Double creditLimitAmount;
    private final LocalDateTime applicationDate;
    private final CreditApplicationResult creditApplicationResult;
}
