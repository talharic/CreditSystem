package com.example.creditsystem.service;

import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.dto.CreditApplicationResultDto;

public interface CreditApplicationService {

    CreditApplicationResultDto saveCreditApplication(CreditApplicationRequestDto creditApplicationRequestDto);

    CreditApplicationResultDto findCreditApplicationByNationalIdNumber(String nationalIdNumber);

}