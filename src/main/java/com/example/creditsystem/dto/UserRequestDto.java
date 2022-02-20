package com.example.creditsystem.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestDto implements Serializable {
    private final String nationalIdNumber;
    private final String name;
    private final String surname;
    private final String phone;
}
