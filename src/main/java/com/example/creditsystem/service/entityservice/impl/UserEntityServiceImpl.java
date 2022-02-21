package com.example.creditsystem.service.entityservice.impl;

import com.example.creditsystem.entity.User;
import com.example.creditsystem.repository.UserRepository;
import com.example.creditsystem.service.entityservice.UserEntityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityServiceImpl extends BaseEntityService<User, UserRepository> implements UserEntityService {

    public UserEntityServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public Optional<User> findByNationalIdNumber(String nationalIdNumber) {
        return getRepository().findByNationalIdNumber(nationalIdNumber);
    }
}
