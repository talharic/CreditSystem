package com.example.creditsystem.controller;

import com.example.creditsystem.dto.CreditApplicationRequestDto;
import com.example.creditsystem.dto.CreditApplicationResponseDto;
import com.example.creditsystem.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/credit-applications")
@CrossOrigin
@RequiredArgsConstructor
public class CreditApplicationController {
    private final CreditApplicationService creditApplicationService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        CreditApplicationResponseDto creditApplicationDto = creditApplicationService.findById(id);
        return ResponseEntity.ok(creditApplicationDto);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllCreditApplications() {
        List<CreditApplicationResponseDto> all = creditApplicationService.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreditApplicationRequestDto creditApplicationRequestDto) {
        return ResponseEntity.ok(creditApplicationService.create(creditApplicationRequestDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        creditApplicationService.deleteById(id);
    }

}
