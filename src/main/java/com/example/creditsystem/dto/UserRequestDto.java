package com.example.creditsystem.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestDto implements Serializable {
    private final Long id;
    private final String nationalIdNumber;
    private final Double monthlyIncome;
    private final String name;
    private final String surname;
    private final String phone;
}
