package com.example.creditsystem.repository;

import com.example.creditsystem.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
}
