package com.example.creditsystem.service.entityservice.impl;

import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.repository.CreditApplicationRepository;
import com.example.creditsystem.service.entityservice.CreditApplicationEntityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditApplicationEntityServiceImpl extends BaseEntityService<CreditApplication, CreditApplicationRepository> implements CreditApplicationEntityService {

    public CreditApplicationEntityServiceImpl(CreditApplicationRepository creditApplicationRepository) {
        super(creditApplicationRepository);
    }

    @Override
    public Optional<CreditApplication> findCreditApplicationByNationalIdNumber(String nationalIdNumber) {
        return getRepository().findCreditApplicationByUserNationalIdNumber(nationalIdNumber);
    }

}