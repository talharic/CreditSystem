package com.example.creditsystem.service.impl;

import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.dto.CreditApplicationResultDto;
import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.enums.CreditApplicationResult;
import com.example.creditsystem.mapper.CreditApplicationMapper;
import com.example.creditsystem.rule.CreditAmountCalculator;
import com.example.creditsystem.service.CreditApplicationService;
import com.example.creditsystem.service.CreditScoreService;
import com.example.creditsystem.service.ValidationService;
import com.example.creditsystem.service.entityservice.CreditApplicationEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditApplicationServiceImpl implements CreditApplicationService {
    private final UserServiceImpl userService;
    private final CreditApplicationEntityService creditApplicationEntityService;
    private final ValidationService validationService;
    private final CreditAmountCalculator creditAmountCalculator;
    private final CreditScoreService creditScoreService;

    @Transactional
    @Override
    public CreditApplicationResultDto saveCreditApplication(CreditApplicationRequestDto creditApplicationRequestDto) {
        CreditApplication creditApplication = CreditApplicationMapper.INSTANCE.convertCreditApplicationRequestDtoToCreditApplication(creditApplicationRequestDto);
        userService.saveUserToEntity(creditApplication.getUser());
        fillCreditApplicationEntity(creditApplication);
        CreditApplication savedApplication = creditApplicationEntityService.save(creditApplication);
        return CreditApplicationMapper.INSTANCE.convertCreditApplicationToCreditApplicationResultDto(savedApplication);
    }

    private void fillCreditApplicationEntity(CreditApplication creditApplication) {
        Long creditScore = creditScoreService.calculateCreditScore(creditApplication.getMonthlyIncome(), creditApplication.getUser().getNationalIdNumber());
        creditApplication.setApplicationDate(LocalDateTime.now());
        creditApplication.setCreditScore(creditScore);
        double creditAmount = creditAmountCalculator.getCreditLimitAmount(creditApplication);
        CreditApplicationResult creditApplicationResult = creditAmount > 0 ? CreditApplicationResult.APPROVED : CreditApplicationResult.REJECTED;
        creditApplication.setCreditApplicationResult(creditApplicationResult);
        creditApplication.setCreditLimitAmount(creditAmount);
    }

    @Override
    public CreditApplicationResultDto findCreditApplicationByNationalIdNumber(String nationalIdNumber) {
        Optional<CreditApplication> creditApplicationByNationalIdNumber = creditApplicationEntityService.findCreditApplicationByNationalIdNumber(nationalIdNumber);
        CreditApplication creditApplication = validationService.validateCreditApplication(creditApplicationByNationalIdNumber);
        return CreditApplicationMapper.INSTANCE.convertCreditApplicationToCreditApplicationResultDto(creditApplication);
    }
}