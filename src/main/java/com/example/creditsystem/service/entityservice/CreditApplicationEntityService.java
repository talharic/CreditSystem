package com.example.creditsystem.service.entityservice;

import com.example.creditsystem.entity.CreditApplication;

import java.util.Optional;

public interface CreditApplicationEntityService extends CrudEntityService<CreditApplication, Long> {
    Optional<CreditApplication> findCreditApplicationByNationalIdNumber(String nationalIdNumber);
}
