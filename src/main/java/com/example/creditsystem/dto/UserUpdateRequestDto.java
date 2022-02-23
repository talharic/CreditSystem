package com.example.creditsystem.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateRequestDto implements Serializable {
    private final String name;
    private final String surname;
    private final String phone;
}
