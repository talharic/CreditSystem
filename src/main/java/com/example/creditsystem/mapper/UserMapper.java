package com.example.creditsystem.mapper;

import com.example.creditsystem.dto.UserRequestDto;
import com.example.creditsystem.dto.UserResponseDto;
import com.example.creditsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserRequestDto convertUserRequestDtoToUser(User user);

    User convertUserRequestDtoToUser(UserRequestDto UserRequestDto);

    UserResponseDto convertUserResponseDtoToUser(User user);

    User convertUserResponseDtoToUser(UserResponseDto UserResponseDto);

    List<UserRequestDto> convertAllUserRequestDtoToUser(List<User> userList);

    List<User> convertAllUserToUserRequestDto(List<UserRequestDto> userRequestDtoList);

    List<User> convertAllUserResponseDtoToUser(List<UserResponseDto> userList);

    List<UserResponseDto> convertAllUserToUserResponseDto(List<User> UserResponseDtoList);
}
