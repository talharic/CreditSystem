package com.example.creditsystem.service.impl;

import com.example.creditsystem.component.notifyMessage.NotifyMessage;
import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.dto.CreditApplicationResultDto;
import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.enums.CreditApplicationResult;
import com.example.creditsystem.factory.NotifyMessageFactory;
import com.example.creditsystem.mapper.CreditApplicationMapper;
import com.example.creditsystem.rule.CreditAmountCalculator;
import com.example.creditsystem.service.*;
import com.example.creditsystem.service.entityservice.CreditApplicationEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditApplicationServiceImpl implements CreditApplicationService {
    private final UserService userService;
    private final CreditApplicationEntityService creditApplicationEntityService;
    private final ValidationService validationService;
    private final CreditAmountCalculator creditAmountCalculator;
    private final CreditScoreService creditScoreService;
    private final UserNotificationService userNotificationService;

    @Transactional
    @Override
    public CreditApplicationResultDto saveCreditApplication(CreditApplicationRequestDto creditApplicationRequestDto) {

        CreditApplication creditApplication = CreditApplicationMapper.INSTANCE.convertCreditApplicationRequestDtoToCreditApplication(creditApplicationRequestDto);
        userService.saveUserToEntity(creditApplication.getUser());
        long creditScore = creditScoreService.calculateCreditScore(creditApplication.getMonthlyIncome(), creditApplication.getUser().getNationalIdNumber());
        double creditAmount = creditAmountCalculator.getCreditLimitAmount(creditScore, creditApplication.getMonthlyIncome());
        CreditApplicationResult creditApplicationResult = creditAmount > 0 ? CreditApplicationResult.APPROVED : CreditApplicationResult.REJECTED;
        fillCreditApplicationEntity(creditApplication, creditScore, creditAmount, creditApplicationResult);
        CreditApplication savedApplication = creditApplicationEntityService.save(creditApplication);
        notifyUser(savedApplication);
        return CreditApplicationMapper.INSTANCE.convertCreditApplicationToCreditApplicationResultDto(savedApplication);
    }

    @Override
    public CreditApplicationResultDto findCreditApplicationByNationalIdNumber(String nationalIdNumber) {
        Optional<CreditApplication> creditApplicationByNationalIdNumber = creditApplicationEntityService.findCreditApplicationByNationalIdNumber(nationalIdNumber);
        CreditApplication creditApplication = validationService.validateCreditApplication(creditApplicationByNationalIdNumber);
        return CreditApplicationMapper.INSTANCE.convertCreditApplicationToCreditApplicationResultDto(creditApplication);
    }

    private void fillCreditApplicationEntity(CreditApplication creditApplication, long creditScore, double creditAmount, CreditApplicationResult creditApplicationResult) {
        log.info("Credit Application Result: " + creditApplicationResult);
        log.info("Credit Limit Amount: " + creditAmount);
        log.info("Credit Score: " + creditScore);
        creditApplication.setApplicationDate(LocalDateTime.now());
        creditApplication.setCreditScore(creditScore);
        creditApplication.setCreditApplicationResult(creditApplicationResult);
        creditApplication.setCreditLimitAmount(creditAmount);
    }

    private void notifyUser(CreditApplication creditApplication) {
        User user = creditApplication.getUser();
        NotifyMessage notifyMessage = NotifyMessageFactory.getNotifyMessage(creditApplication.getCreditApplicationResult().getResult());
        String generatedMessage = notifyMessage.getMessage(user.getName() + " " + user.getSurname(), creditApplication.getCreditLimitAmount());
        userNotificationService.notifyUser(creditApplication.getUser(), generatedMessage);
    }

}
