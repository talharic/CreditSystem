package com.example.creditsystem.service.entityservice;

import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.repository.CreditApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditApplicationEntityService extends BaseEntityService<CreditApplication, CreditApplicationRepository> {

    public CreditApplicationEntityService(CreditApplicationRepository creditApplicationRepository) {
        super(creditApplicationRepository);

    }

    public Optional<List<CreditApplication>> findCreditApplicationByNationalIdNumber(String nationalIdNumber) {
        return getRepository().findCreditApplicationByUserNationalIdNumber(nationalIdNumber);
    }
}
