package com.example.creditsystem.mapper;

import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.dto.CreditApplicationResultDto;
import com.example.creditsystem.entity.CreditApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditApplicationMapper {
    CreditApplicationMapper INSTANCE = Mappers.getMapper(CreditApplicationMapper.class);

    @Mapping(target = "nationalIdNumber", source = "user.nationalIdNumber")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "surname", source = "user.surname")
    @Mapping(target = "phone", source = "user.phone")
    CreditApplicationRequestDto convertCreditApplicationToCreditApplicationRequestDto(CreditApplication creditApplication);

    @Mapping(source = "nationalIdNumber", target = "user.nationalIdNumber")
    @Mapping(source = "name", target = "user.name")
    @Mapping(source = "surname", target = "user.surname")
    @Mapping(source = "phone", target = "user.phone")
    CreditApplication convertCreditApplicationRequestDtoToCreditApplication(CreditApplicationRequestDto CreditApplicationRequestDto);

    @Mapping(target = "userNationalIdNumber", source = "user.nationalIdNumber")
    List<CreditApplicationRequestDto> convertAllCreditApplicationRequestDtoToCreditApplication(List<CreditApplication> creditApplicationList);

    @Mapping(source = "userNationalIdNumber", target = "user.nationalIdNumber")
    List<CreditApplication> convertAllCreditApplicationToCreditApplicationRequestDto(List<CreditApplicationRequestDto> creditApplicationRequestDtoList);

    @Mapping(target = "nationalIdNumber", source = "user.nationalIdNumber")
    CreditApplicationResultDto convertCreditApplicationToCreditApplicationResultDto(CreditApplication creditApplication);

    @Mapping(target = "nationalIdNumber", source = "user.nationalIdNumber")
    List<CreditApplicationResultDto> convertAllCreditApplicationToCreditApplicationResultDto(List<CreditApplication> creditApplicationList);

    @Mapping(source = "nationalIdNumber", target = "user.nationalIdNumber")
    CreditApplication convertCreditApplicationResultDtoToCreditApplication(CreditApplicationResultDto creditApplicationResultDto);
}