package com.example.creditsystem.service.entityservice;

import com.example.creditsystem.entity.CreditScore;
import com.example.creditsystem.repository.CreditScoreRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditScoreEntityService extends BaseEntityService<CreditScore, CreditScoreRepository> {
    public CreditScoreEntityService(CreditScoreRepository CreditScoreRepository) {
        super(CreditScoreRepository);
    }
}
