package com.example.creditsystem.dto;

import com.example.creditsystem.enums.CreditApplicationResult;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CreditApplicationResponseDto implements Serializable {

    @NotBlank(message = "National Id Number is mandatory")
    @Pattern(regexp = "[\\d]{11}", message = "National Id Number should contain 11 digits!")
    private final String userNationalIdNumber;

    @NotBlank(message = "Name is mandatory")
    private final String userName;

    @NotBlank(message = "Surname is mandatory")
    private final String userSurname;

    @NotBlank(message = "Phone is mandatory")
    private final String userPhone;

    @NotNull(message = "Monthly Income is mandatory")
    private final Double monthlyIncome;

    private final Double creditLimitAmount;
    private final LocalDateTime applicationDate;
    private final CreditApplicationResult creditApplicationResult;
}
