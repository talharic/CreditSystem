package com.example.creditsystem.service;

import com.example.creditsystem.component.notifyMessage.ApprovedNotifyMessage;
import com.example.creditsystem.component.notifyMessage.NotifyMessage;
import com.example.creditsystem.component.notifyMessage.RejectedNotifyMessage;
import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.dto.CreditApplicationResultDto;
import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.enums.CreditApplicationResult;
import com.example.creditsystem.factory.NotifyMessageFactory;
import com.example.creditsystem.mapper.CreditApplicationMapper;
import com.example.creditsystem.rule.CreditAmountCalculator;
import com.example.creditsystem.service.entityservice.CreditApplicationEntityService;
import com.example.creditsystem.service.impl.CreditApplicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceTest {

    @Mock
    UserService userService;

    @Mock
    CreditApplicationEntityService creditApplicationEntityService;

    @Mock
    ValidationService validationService;

    @Mock
    CreditAmountCalculator creditAmountCalculator;

    @Mock
    CreditScoreService creditScoreService;

    @Mock
    UserNotificationService userNotificationService;

    @Mock
    NotifyMessageFactory notifyMessageFactory;

    @InjectMocks
    CreditApplicationServiceImpl creditApplicationService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveApprovedCreditApplication() {
        CreditApplication expectedCreditApplication = getApprovedCreditApplication();
        saveCreditApplication(expectedCreditApplication);
    }

    @Test
    void saveRejectedCreditApplication() {
        CreditApplication expectedCreditApplication = getRejectedCreditApplication();
        saveCreditApplication(expectedCreditApplication);
    }

    @Test
    void findCreditApplicationByNationalIdNumber() {

        CreditApplication expectedCreditApplication = getApprovedCreditApplication();

        when(creditApplicationEntityService.findCreditApplicationByNationalIdNumber
                (expectedCreditApplication.getUser().getNationalIdNumber()))
                .thenReturn(Optional.of(expectedCreditApplication));

        when(validationService.validateCreditApplication
                (Optional.of(expectedCreditApplication)))
                .thenReturn(expectedCreditApplication);

        CreditApplicationResultDto expected =
                CreditApplicationMapper.INSTANCE.convertCreditApplicationToCreditApplicationResultDto(expectedCreditApplication);

        CreditApplicationResultDto actual =
                creditApplicationService.findCreditApplicationByNationalIdNumber(expectedCreditApplication.getUser().getNationalIdNumber());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    private void saveCreditApplication(CreditApplication expectedCreditApplication) {

        initNotifyFactory();

        CreditApplicationRequestDto creditApplicationRequestDto =
                CreditApplicationMapper.INSTANCE.convertCreditApplicationToCreditApplicationRequestDto(expectedCreditApplication);

        when(userService.saveUserToEntity(any())).thenReturn(expectedCreditApplication.getUser());

        when(creditScoreService.calculateCreditScore(expectedCreditApplication
                .getMonthlyIncome(), expectedCreditApplication.getUser().getNationalIdNumber()))
                .thenReturn(expectedCreditApplication.getCreditScore());

        when(creditAmountCalculator.getCreditLimitAmount(expectedCreditApplication
                .getCreditScore(), expectedCreditApplication.getMonthlyIncome()))
                .thenReturn(expectedCreditApplication.getCreditLimitAmount());

        when(creditApplicationEntityService.save(any(CreditApplication.class)))
                .thenReturn(expectedCreditApplication);

        CreditApplicationResultDto expected =
                CreditApplicationMapper.INSTANCE.convertCreditApplicationToCreditApplicationResultDto(expectedCreditApplication);

        CreditApplicationResultDto actual = creditApplicationService.saveCreditApplication(creditApplicationRequestDto);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    private User getUser() {
        User user1 = new User();
        user1.setId(1L);
        user1.setNationalIdNumber("12585497585");
        user1.setPhone("5395893797");
        user1.setName("Talha");
        return user1;
    }

    private CreditApplication getApprovedCreditApplication() {
        CreditApplication creditApplication = new CreditApplication();
        creditApplication.setUser(getUser());
        creditApplication.setCreditScore(750L);
        creditApplication.setMonthlyIncome(10000.0);
        creditApplication.setCreditApplicationResult(CreditApplicationResult.APPROVED);
        creditApplication.setCreditLimitAmount(30000.0);
        return creditApplication;
    }

    private CreditApplication getRejectedCreditApplication() {
        CreditApplication creditApplication = new CreditApplication();
        creditApplication.setUser(getUser());
        creditApplication.setCreditScore(300L);
        creditApplication.setMonthlyIncome(12000.0);
        creditApplication.setCreditApplicationResult(CreditApplicationResult.REJECTED);
        creditApplication.setCreditLimitAmount(0.0);
        return creditApplication;
    }

    private void initNotifyFactory() {
        List<NotifyMessage> notifyMessages = new ArrayList<>();
        notifyMessages.add(new ApprovedNotifyMessage());
        notifyMessages.add(new RejectedNotifyMessage());
        notifyMessageFactory = new NotifyMessageFactory(notifyMessages);
        notifyMessageFactory.initNotifyMessageCache();
    }

}
