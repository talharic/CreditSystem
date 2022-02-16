package com.example.creditsystem.repository;

import com.example.creditsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNationalIdNumber(String nationalIdNumber);
}
