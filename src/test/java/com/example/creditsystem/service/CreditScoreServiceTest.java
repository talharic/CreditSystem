package com.example.creditsystem.service;

import com.example.creditsystem.service.impl.CreditScoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreditScoreServiceTest {

    @InjectMocks
    CreditScoreService creditScoreService;

    @BeforeEach
    void setUp() {
        creditScoreService = mock(CreditScoreServiceImpl.class);
    }

    @Test
    void shouldCalculateCreditScoreZeroWithNullParams() {
        when(creditScoreService.calculateCreditScore(null, null)).thenReturn(0L);
    }

    @Test
    void shouldCalculateCreditScoreZeroZeroNullParams() {
        when(creditScoreService.calculateCreditScore((double) 0L, "12585497585")).thenReturn(0L);
    }
}
