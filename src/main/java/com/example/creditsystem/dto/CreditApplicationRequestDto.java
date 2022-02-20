package com.example.creditsystem.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class CreditApplicationRequestDto implements Serializable {
    private final String userNationalIdNumber;
    private final Double monthlyIncome;
}
