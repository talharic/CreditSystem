package com.example.creditsystem.service.entityservice;

import com.example.creditsystem.entity.User;
import com.example.creditsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {
    public UserEntityService(UserRepository userRepository) {
        super(userRepository);
    }

    public Optional<User> findByNationalIdNumber(String nationalIdNumber) {
        return getRepository().findByNationalIdNumber(nationalIdNumber);
    }
}
