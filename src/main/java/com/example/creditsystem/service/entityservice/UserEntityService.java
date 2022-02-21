package com.example.creditsystem.service.entityservice;

import com.example.creditsystem.entity.User;

import java.util.Optional;

public interface UserEntityService extends CrudEntityService<User, Long> {
    Optional<User> findByNationalIdNumber(String nationalIdNumber);
}
