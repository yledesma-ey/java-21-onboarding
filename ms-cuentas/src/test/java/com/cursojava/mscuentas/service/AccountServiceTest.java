package com.cursojava.mscuentas.service;

import com.cursojava.mscuentas.model.entity.Account;
import com.cursojava.mscuentas.model.entity.AccountStatus;
import com.cursojava.mscuentas.model.entity.Currency;
import com.cursojava.mscuentas.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private AccountStatusService accountStatusService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testProcessAccountCreation_CuentaPesos() {
        Long persnum = 123456L;
        String message = "Cuenta PESOS";
        Currency pesosCurrency = new Currency();
        pesosCurrency.setSymbol("ARG");
        AccountStatus activeStatus = new AccountStatus();
        activeStatus.setDetail("ACTIVA");

        when(currencyService.findBySymbol("ARG")).thenReturn(pesosCurrency);
        when(accountStatusService.findByDetail("ACTIVA")).thenReturn(activeStatus);
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        accountService.processAccountCreation(persnum, message);

        verify(currencyService).findBySymbol("ARG");
        verify(accountStatusService).findByDetail("ACTIVA");
    }

    @Test
    void testProcessAccountCreation_CuentaDolarYPesos() {
        Long persnum = 123456L;
        String message = "Cuenta Dólar y pesos";
        Currency usdCurrency = new Currency();
        usdCurrency.setSymbol("USD");
        Currency pesosCurrency = new Currency();
        pesosCurrency.setSymbol("ARG");
        AccountStatus activeStatus = new AccountStatus();
        activeStatus.setDetail("ACTIVA");

        when(currencyService.findBySymbol("USD")).thenReturn(usdCurrency);
        when(currencyService.findBySymbol("ARG")).thenReturn(pesosCurrency);
        when(accountStatusService.findByDetail("ACTIVA")).thenReturn(activeStatus);
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        accountService.processAccountCreation(persnum, message);

        // Assert
        verify(currencyService).findBySymbol("USD");
        verify(currencyService).findBySymbol("ARG");
        verify(accountStatusService, times(2)).findByDetail("ACTIVA");
        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    void testProcessAccountCreation_InvalidMessage() {
        Long persnum = 123456L;
        String invalidMessage = "Invalid Message";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.processAccountCreation(persnum, invalidMessage);
        });

        assertEquals("Invalid message: Invalid Message", exception.getMessage());
    }
}
