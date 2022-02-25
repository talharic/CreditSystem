package com.example.creditsystem.mapper;

import com.example.creditsystem.dto.UserResponseDto;
import com.example.creditsystem.dto.UserUpdateRequestDto;
import com.example.creditsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserUpdateRequestDto convertUserToUserToUserUpdateRequestDto(User user);

    User convertUserUpdateRequestDtoToUser(UserUpdateRequestDto UserUpdateRequestDto);

    @Mapping(target = "monthlyIncome", source = "creditApplication.monthlyIncome")
    UserResponseDto convertUserToUserResponseDto(User user);

    @Mapping(source = "monthlyIncome", target = "creditApplication.monthlyIncome")
    User convertUserResponseDtoToUser(UserResponseDto UserResponseDto);

    @Mapping(source = "monthlyIncome", target = "creditApplication.monthlyIncome")
    List<User> convertAllUserResponseDtoToUser(List<UserResponseDto> userList);

    @Mapping(target = "monthlyIncome", source = "creditApplication.monthlyIncome")
    List<UserResponseDto> convertAllUserToUserResponseDto(List<User> UserResponseDtoList);
}
