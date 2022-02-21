package com.example.creditsystem.dto;

import com.example.creditsystem.enums.CreditApplicationResult;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CreditApplicationResultDto implements Serializable {
    private final String nationalIdNumber;
    private final Double creditLimitAmount;
    private final LocalDateTime applicationDate;
    private final CreditApplicationResult creditApplicationResult;
}
