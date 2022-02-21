package com.example.creditsystem.service;

import com.example.creditsystem.dto.UserRequestDto;
import com.example.creditsystem.dto.UserResponseDto;
import com.example.creditsystem.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll();

    User saveUserToEntity(User user);

    UserResponseDto update(UserRequestDto userRequestDto, String nationalIdNumber);

    UserResponseDto findByNationalIdNumber(String nationalIdNumber);

    void deleteByNationalIdNumber(String nationalIdNumber);

    User findUserByNationalIdNumber(String nationalIdNumber);

}
