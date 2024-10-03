package com.cursojava.mscuentas.repository;


import com.cursojava.mscuentas.model.entity.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CurrencyRepositoryTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCurrencyBySymbol() {
        String symbol = "ARS";
        Currency currency = new Currency();
        currency.setCode(6L);
        currency.setPais("Argentina");
        currency.setSymbol("ARS");
        when(currencyRepository.findBySymbol(symbol)).thenReturn(Optional.of(currency));

        Optional<Currency> currencyFound = currencyRepository.findBySymbol(symbol);

        assertEquals(currency, currencyFound.get());
    }
}
