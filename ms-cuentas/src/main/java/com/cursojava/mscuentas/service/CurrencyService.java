package com.cursojava.mscuentas.service;

import com.cursojava.mscuentas.model.entity.Currency;
import com.cursojava.mscuentas.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Currency findBySymbol(String symbol) {
        return currencyRepository.findBySymbol(symbol).orElseThrow(() -> new IllegalArgumentException("Codigo no válido: " + symbol));
    }

    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }
}
