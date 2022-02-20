package com.example.creditsystem.repository;

import com.example.creditsystem.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {

    Optional<List<CreditApplication>> findCreditApplicationByUserNationalIdNumber(String nationalIdNumber);

}