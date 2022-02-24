package com.example.creditsystem.service;

import com.example.creditsystem.dto.UserResponseDto;
import com.example.creditsystem.dto.UserUpdateRequestDto;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.mapper.UserMapper;
import com.example.creditsystem.service.entityservice.UserEntityService;
import com.example.creditsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserEntityService userEntityService;
    @Mock
    ValidationService validationService;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAll() {
        List<User> expected = getTwoUser();

        when(userEntityService.findAll()).thenReturn(expected);

        List<UserResponseDto> userResponseDtoList = UserMapper.INSTANCE.convertAllUserToUserResponseDto(expected);

        List<UserResponseDto> actual = userService.findAll();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(userResponseDtoList.size(), actual.size()),
                () -> assertEquals(userResponseDtoList, actual)
        );
    }

    @Test
    void saveUserToEntity() {
        User expected = getUser();
        when(userEntityService.findByNationalIdNumber(anyString())).thenReturn(Optional.of(expected));
        when(validationService.validateUserNotExist(Optional.of(expected))).thenReturn(Boolean.TRUE);
        when(validationService.validateNationalIdNumber(anyString())).thenReturn(Boolean.TRUE);
        when(userEntityService.save(expected)).thenReturn(expected);

        User actual = userService.saveUserToEntity(expected);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void update() {

        User expectedUser = getUser();
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto(expectedUser.getName(), expectedUser.getSurname(), expectedUser.getPhone());
        when(userService.findUserByNationalIdNumber(anyString())).thenReturn(expectedUser);
        when(userEntityService.save(expectedUser)).thenReturn(expectedUser);
        UserResponseDto expected = UserMapper.INSTANCE.convertUserToUserResponseDto(expectedUser);
        UserResponseDto actual = userService.update(userUpdateRequestDto, expected.getNationalIdNumber());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }


    @Test
    void findByNationalIdNumber() {
        User expectedUser = getUser();
        when(userEntityService.findByNationalIdNumber(anyString())).thenReturn(Optional.of(expectedUser));
        when(validationService.validateUser(Optional.of(expectedUser))).thenReturn(expectedUser);

        UserResponseDto expected = UserMapper.INSTANCE.convertUserToUserResponseDto(expectedUser);
        UserResponseDto actual = userService.findByNationalIdNumber(anyString());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteByNationalIdNumber() {

        User expectedUser = getUser();

        when(userService.findUserByNationalIdNumber(expectedUser.getNationalIdNumber())).thenReturn(expectedUser);

        userService.deleteByNationalIdNumber(expectedUser.getNationalIdNumber());

        verify(userEntityService, times(1)).delete(expectedUser);
    }


    @Test
    void findUserByNationalIdNumber() {
        User expected = getUser();
        when(userEntityService.findByNationalIdNumber(anyString())).thenReturn(Optional.of(expected));
        when(validationService.validateUser(Optional.of(expected))).thenReturn(expected);
        User actual = userService.findUserByNationalIdNumber(anyString());
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    private List<User> getTwoUser() {
        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setId(1L);
        user1.setNationalIdNumber("12585497585");
        user1.setPhone("5395893797");
        user1.setName("Talha");

        User user2 = new User();
        user2.setId(2L);
        user2.setNationalIdNumber("96385297585");
        user2.setPhone("5337694739");
        user2.setName("Deneme isim");

        userList.add(user1);
        userList.add(user2);

        return userList;
    }

    private User getUser() {
        User user1 = new User();
        user1.setId(1L);
        user1.setNationalIdNumber("12585497585");
        user1.setPhone("5395893797");
        user1.setName("Talha");
        return user1;
    }

}
