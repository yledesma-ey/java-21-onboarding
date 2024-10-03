package com.cursojava.mscuentas.service;

import com.cursojava.mscuentas.model.entity.Account;
import com.cursojava.mscuentas.model.entity.AccountStatus;
import com.cursojava.mscuentas.model.entity.Currency;
import com.cursojava.mscuentas.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    private final CurrencyService currencyService;

    private final AccountStatusService accountStatusService;

    public AccountService(AccountRepository accountRepository, CurrencyService currencyService, AccountStatusService accountStatusService) {
        this.accountRepository = accountRepository;
        this.currencyService = currencyService;
        this.accountStatusService = accountStatusService;
    }

    public void processAccountCreation(Long persnum, String message) {
        switch (message) {
            case "Cuenta PESOS":
                createAccount(persnum, currencyService.findBySymbol("ARG"));
                break;
            case "Cuenta Dólar y pesos":
                createAccounts(persnum);
                break;
            default:
                throw new IllegalArgumentException("Invalid message: " + message);
        }
    }

    private void createAccounts(Long persnum) {
        log.info("Creating accounts for persnum: " + persnum);
        CompletableFuture<Account> usdAccountFuture = createAccount(persnum, currencyService.findBySymbol("USD"));
        CompletableFuture<Account> pesAccountFuture = createAccount(persnum, currencyService.findBySymbol("ARG"));

        CompletableFuture.allOf(usdAccountFuture, pesAccountFuture)
                .thenRun(() -> {
                    Account usdAccount = usdAccountFuture.join();
                    Account pesAccount = pesAccountFuture.join();
                    log.info("Created accounts: USD n° - {}, PES n° - {}", usdAccount.getNumCuenta(), pesAccount.getNumCuenta());
                })
                .exceptionally(e -> {
                    log.error("Error creating accounts for persnum: {}", persnum, e);
                    return null;
                });
    }

    private CompletableFuture<Account> createAccount(Long persnum, Currency currency) {
        AccountStatus accountStatus = accountStatusService.findByDetail("ACTIVA");
        return CompletableFuture.supplyAsync(() -> {
            log.info("Creating account for persnum: {}, currency: {}, estado: {}", persnum, currency.getSymbol(), accountStatus.getDetail());
            Account account = Account.builder()
                    .numCuenta(generateAccountNumber(persnum))
                    .persnum(persnum)
                    .currency(currency)
                    .estado(accountStatus)
                    .saldo(BigDecimal.valueOf(0.0))
                    .build();

            return accountRepository.save(account);
        });
    }

    private Long generateAccountNumber(Long persnum) {
        long timestamp = System.currentTimeMillis();
        return Long.parseLong(persnum.toString()  + timestamp);
    }
}
