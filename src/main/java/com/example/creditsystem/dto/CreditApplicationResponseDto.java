package com.example.creditsystem.dto;

import com.example.creditsystem.enums.CreditApplicationResult;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CreditApplicationResponseDto implements Serializable {

    @NotBlank(message = "National Id Number is mandatory")
    @Size(min = 11, max = 11, message = "National Id Number should be 11 characters.")
    private final String userNationalIdNumber;

    @NotBlank(message = "Name is mandatory")
    private final String userName;

    @NotBlank(message = "Surname is mandatory")
    private final String userSurname;

    @NotBlank(message = "Phone is mandatory")
    private final String userPhone;

    @NotBlank(message = "Monthly Income is mandatory")
    private final Double monthlyIncome;

    private final Double creditLimitAmount;
    private final LocalDateTime applicationDate;
    private final CreditApplicationResult creditApplicationResult;
}
