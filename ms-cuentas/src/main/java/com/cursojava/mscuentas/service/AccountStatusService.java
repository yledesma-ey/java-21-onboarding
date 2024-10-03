package com.cursojava.mscuentas.service;

import com.cursojava.mscuentas.model.entity.AccountStatus;
import com.cursojava.mscuentas.repository.AccountStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountStatusService {

    private final AccountStatusRepository accountStatusRepository;

    public AccountStatusService(AccountStatusRepository accountStatusRepository) {
        this.accountStatusRepository = accountStatusRepository;
    }

    public AccountStatus findByDetail(String detail) {
        return accountStatusRepository.findByDetail(detail).orElseThrow(() -> new IllegalArgumentException("Codigo no válido"));
    }


}
