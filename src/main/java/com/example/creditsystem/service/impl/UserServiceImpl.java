package com.example.creditsystem.service.impl;

import com.example.creditsystem.dto.UserResponseDto;
import com.example.creditsystem.dto.UserUpdateRequestDto;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.exception.UserAlreadyExistException;
import com.example.creditsystem.mapper.UserMapper;
import com.example.creditsystem.service.UserService;
import com.example.creditsystem.service.ValidationService;
import com.example.creditsystem.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEntityService userEntityService;
    private final ValidationService validationService;

    @Override
    public List<UserResponseDto> findAll() {
        List<User> userList = userEntityService.findAll();
        return UserMapper.INSTANCE.convertAllUserToUserResponseDto(userList);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public User saveUserToEntity(User user) {
        Optional<User> byNationalIdNumber = userEntityService.findByNationalIdNumber(user.getNationalIdNumber());
        if (byNationalIdNumber.isPresent())
            throw new UserAlreadyExistException("User has already exist.");
        return userEntityService.save(user);
    }

    @Transactional
    @Override
    public UserResponseDto update(UserUpdateRequestDto userUpdateRequestDto, String nationalIdNumber) {
        User userRequestEntity = UserMapper.INSTANCE.convertUserUpdateRequestDtoToUser(userUpdateRequestDto);
        User user = findUserByNationalIdNumber(nationalIdNumber);
        fillUserProperties(userRequestEntity, user);
        User updatedUser = userEntityService.save(user);
        return UserMapper.INSTANCE.convertUserResponseDtoToUser(updatedUser);
    }

    @Override
    public UserResponseDto findByNationalIdNumber(String nationalIdNumber) {
        User userByNationalIdNumber = findUserByNationalIdNumber(nationalIdNumber);
        return UserMapper.INSTANCE.convertUserResponseDtoToUser(userByNationalIdNumber);
    }

    @Transactional
    @Override
    public void deleteByNationalIdNumber(String nationalIdNumber) {
        User user = findUserByNationalIdNumber(nationalIdNumber);
        userEntityService.delete(user);
    }

    @Override
    public User findUserByNationalIdNumber(String nationalIdNumber) {
        Optional<User> optionalUser = userEntityService.findByNationalIdNumber(nationalIdNumber);
        return validationService.validateUser(optionalUser);
    }

    private void fillUserProperties(User userRequestEntity, User user) {
        user.setName(userRequestEntity.getName());
        user.setSurname(userRequestEntity.getSurname());
        user.setPhone(userRequestEntity.getPhone());
    }
}
