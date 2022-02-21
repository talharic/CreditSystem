package com.example.creditsystem.service;

import com.example.creditsystem.dto.UserRequestDto;
import com.example.creditsystem.dto.UserResponseDto;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.exception.UserAlreadyExistException;
import com.example.creditsystem.mapper.UserMapper;
import com.example.creditsystem.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityService userEntityService;
    private final ValidationService validationService;

    public List<UserResponseDto> findAll() {
        List<User> userList = userEntityService.findAll();
        return UserMapper.INSTANCE.convertAllUserToUserResponseDto(userList);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public User saveUserToEntity(User user) {
        Optional<User> byNationalIdNumber = userEntityService.findByNationalIdNumber(user.getNationalIdNumber());
        if (byNationalIdNumber.isPresent())
            throw new UserAlreadyExistException("User has already exist.");
        return userEntityService.save(user);
    }

    @Transactional
    public UserResponseDto update(UserRequestDto userRequestDto, String nationalIdNumber) {
        User userRequestEntity = UserMapper.INSTANCE.convertUserRequestDtoToUser(userRequestDto);
        User user = findUserByNationalIdNumber(nationalIdNumber);
        fillUserProperties(userRequestEntity, user);
        User updatedUser = userEntityService.save(user);
        return UserMapper.INSTANCE.convertUserResponseDtoToUser(updatedUser);
    }


    public UserResponseDto findByNationalIdNumber(String nationalIdNumber) {
        User userByNationalIdNumber = findUserByNationalIdNumber(nationalIdNumber);
        return UserMapper.INSTANCE.convertUserResponseDtoToUser(userByNationalIdNumber);
    }

    @Transactional
    public void deleteByNationalIdNumber(String nationalIdNumber) {
        User user = findUserByNationalIdNumber(nationalIdNumber);
        userEntityService.delete(user);
    }


    protected User findUserByNationalIdNumber(String nationalIdNumber) {
        Optional<User> optionalUser = userEntityService.findByNationalIdNumber(nationalIdNumber);
        return validationService.validateUser(optionalUser);
    }

    private void fillUserProperties(User userRequestEntity, User user) {
        user.setName(userRequestEntity.getName());
        user.setSurname(userRequestEntity.getSurname());
        user.setPhone(userRequestEntity.getPhone());
        user.setNationalIdNumber(userRequestEntity.getNationalIdNumber());
    }
}
