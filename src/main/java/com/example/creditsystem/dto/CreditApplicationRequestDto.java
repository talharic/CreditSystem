package com.example.creditsystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CreditApplicationRequestDto implements Serializable {
    @NotBlank(message = "National Id Number is mandatory")
    @Size(min = 11, max = 11, message = "National Id Number should be 11 characters.")
    private final String userNationalIdNumber;
    @NotBlank(message = "Monthly Income is mandatory")
    private final Double monthlyIncome;
}
