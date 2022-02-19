package com.example.creditsystem.mapper;

import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.dto.CreditApplicationResponseDto;
import com.example.creditsystem.entity.CreditApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditApplicationMapper {
    CreditApplicationMapper INSTANCE = Mappers.getMapper(CreditApplicationMapper.class);

    CreditApplicationRequestDto convertCreditApplicationRequestDtoToCreditApplication(CreditApplication creditApplication);

    @Mapping(source = "userNationalIdNumber", target = "user.nationalIdNumber")
    CreditApplication convertCreditApplicationRequestDtoToCreditApplication(CreditApplicationRequestDto CreditApplicationRequestDto);

    CreditApplicationResponseDto convertCreditApplicationResponseDtoToCreditApplication(CreditApplication creditApplication);

    CreditApplication convertCreditApplicationResponseDtoToCreditApplication(CreditApplicationResponseDto CreditApplicationResponseDto);

    List<CreditApplicationRequestDto> convertAllCreditApplicationRequestDtoToCreditApplication(List<CreditApplication> creditApplicationList);

    List<CreditApplication> convertAllCreditApplicationToCreditApplicationRequestDto(List<CreditApplicationRequestDto> creditApplicationRequestDtoList);

    List<CreditApplication> convertAllCreditApplicationResponseDtoToCreditApplication(List<CreditApplicationResponseDto> creditApplicationList);

    List<CreditApplicationResponseDto> convertAllCreditApplicationToCreditApplicationResponseDto(List<CreditApplication> CreditApplicationResponseDtoList);
}
