package com.example.creditsystem.service;

import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.dto.CreditApplicationResponseDto;
import com.example.creditsystem.dto.CreditCalculationDto;
import com.example.creditsystem.entity.CreditApplication;
import com.example.creditsystem.entity.User;
import com.example.creditsystem.enums.CreditApplicationResult;
import com.example.creditsystem.mapper.CreditApplicationMapper;
import com.example.creditsystem.rule.CreditAmountCalculator;
import com.example.creditsystem.service.entityservice.CreditApplicationEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditApplicationService {
    private final UserService userService;
    private final CreditApplicationEntityService creditApplicationEntityService;
    private final ValidationService validationService;
    private final CreditAmountCalculator creditAmountCalculator;

    @Transactional
    public CreditApplicationResponseDto create(CreditApplicationRequestDto creditApplicationRequestDto) {
        CreditApplication creditApplication = CreditApplicationMapper.INSTANCE.convertCreditApplicationRequestDtoToCreditApplication(creditApplicationRequestDto);
        User user = creditApplication.getUser();
        user = userService.findUserByNationalIdNumber(user.getNationalIdNumber());
        creditApplication.setUser(user);
        creditApplication.setApplicationDate(LocalDateTime.now());
        calculateCreditLimit(creditApplication, user);
        CreditApplication savedApplication = creditApplicationEntityService.save(creditApplication);
        return CreditApplicationMapper.INSTANCE.convertCreditApplicationResponseDtoToCreditApplication(savedApplication);
    }

    private void calculateCreditLimit(CreditApplication creditApplication, User user) {
        double creditAmount = creditAmountCalculator.getCreditAmount(new CreditCalculationDto(user.getMonthlyIncome(), user.getCreditScore()));
        CreditApplicationResult creditApplicationResult = creditAmount > 0 ? CreditApplicationResult.APPROVED : CreditApplicationResult.REJECTED;
        creditApplication.setCreditApplicationResult(creditApplicationResult);
        creditApplication.setCreditLimitAmount(creditAmount);
    }

    public CreditApplicationResponseDto findById(Long id) {
        CreditApplication creditApplicationById = findCreditApplicationById(id);
        return CreditApplicationMapper.INSTANCE.convertCreditApplicationResponseDtoToCreditApplication(creditApplicationById);
    }

    @Transactional
    public void deleteById(Long id) {
        CreditApplication creditApplication = findCreditApplicationById(id);
        creditApplicationEntityService.delete(creditApplication);
    }

    private CreditApplication findCreditApplicationById(Long id) {
        Optional<CreditApplication> creditApplicationById = creditApplicationEntityService.findById(id);
        return validationService.validateCreditApplication(creditApplicationById);
    }

    public List<CreditApplicationResponseDto> findAll() {

        List<CreditApplication> creditApplicationList = creditApplicationEntityService.findAll();

        return CreditApplicationMapper.INSTANCE.convertAllCreditApplicationToCreditApplicationResponseDto(creditApplicationList);
    }


}
