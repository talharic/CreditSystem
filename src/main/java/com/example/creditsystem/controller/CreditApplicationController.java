package com.example.creditsystem.controller;

import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/credit-applications")
@CrossOrigin
@RequiredArgsConstructor
public class CreditApplicationController {
    private final CreditApplicationService creditApplicationService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreditApplicationRequestDto creditApplicationRequestDto) {
        return ResponseEntity.ok(creditApplicationService.saveCreditApplication(creditApplicationRequestDto));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getByByNationalIdNumberAndBirthDate(@RequestParam("id") String nationalIdNumber) {
        return ResponseEntity.ok(creditApplicationService.findCreditApplicationByNationalIdNumber(nationalIdNumber));
    }

}
