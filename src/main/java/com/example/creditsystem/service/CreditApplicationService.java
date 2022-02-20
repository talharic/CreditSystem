package com.example.creditsystem.service;

import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.dto.CreditApplicationResultDto;
import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.enums.CreditApplicationResult;
import com.example.creditsystem.mapper.CreditApplicationMapper;
import com.example.creditsystem.rule.CreditAmountCalculator;
import com.example.creditsystem.service.entityservice.CreditApplicationEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditApplicationService {
    private final UserService userService;
    private final CreditApplicationEntityService creditApplicationEntityService;
    private final ValidationService validationService;
    private final CreditAmountCalculator creditAmountCalculator;
    private final CreditScoreService creditScoreService;

    @Transactional
    public CreditApplicationResultDto create(CreditApplicationRequestDto creditApplicationRequestDto) {
        CreditApplication creditApplication = CreditApplicationMapper.INSTANCE.convertCreditApplicationRequestDtoToCreditApplication(creditApplicationRequestDto);
        User userFromDto = creditApplication.getUser();
        User user = userService.findUserByNationalIdNumber(userFromDto.getNationalIdNumber());
        fillCreditApplicationEntity(creditApplication, user);
        CreditApplication savedApplication = creditApplicationEntityService.save(creditApplication);
        return CreditApplicationMapper.INSTANCE.convertCreditApplicationToCreditApplicationResultDto(savedApplication);
    }

    private void fillCreditApplicationEntity(CreditApplication creditApplication, User user) {
        Long creditScore = creditScoreService.calculateCreditScore(creditApplication.getMonthlyIncome(), user.getNationalIdNumber());
        creditApplication.setUser(user);
        creditApplication.setApplicationDate(LocalDateTime.now());
        creditApplication.setCreditScore(creditScore);
        double creditAmount = creditAmountCalculator.getCreditLimitAmount(creditApplication);
        CreditApplicationResult creditApplicationResult = creditAmount > 0 ? CreditApplicationResult.APPROVED : CreditApplicationResult.REJECTED;
        creditApplication.setCreditApplicationResult(creditApplicationResult);
        creditApplication.setCreditLimitAmount(creditAmount);
    }


    public List<CreditApplicationResultDto> findCreditApplicationByNationalIdNumber(String nationalIdNumber) {
        Optional<List<CreditApplication>> creditApplicationByNationalIdNumber = creditApplicationEntityService.findCreditApplicationByNationalIdNumber(nationalIdNumber);
        List<CreditApplication> creditApplicationList = validationService.validateCreditApplicationList(creditApplicationByNationalIdNumber);
        return CreditApplicationMapper.INSTANCE.convertAllCreditApplicationToCreditApplicationResultDto(creditApplicationList);
    }
}
