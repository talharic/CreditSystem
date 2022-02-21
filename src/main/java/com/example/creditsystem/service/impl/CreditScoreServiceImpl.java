package com.example.creditsystem.service.impl;

import com.example.creditsystem.service.CreditScoreService;
import org.springframework.stereotype.Service;

@Service
public class CreditScoreServiceImpl implements CreditScoreService {

    @Override
    public Long calculateCreditScore(Double monthlyIncome, String nationalIdNumber) {
        return 1000L;
    }
}