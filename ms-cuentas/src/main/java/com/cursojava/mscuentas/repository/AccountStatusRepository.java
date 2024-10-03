package com.cursojava.mscuentas.repository;

import com.cursojava.mscuentas.model.entity.AccountStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountStatusRepository extends CrudRepository<AccountStatus, Long> {

    Optional<AccountStatus> findByDetail(String detail);
}
