package com.cursojava.mscuentas.repository;

import com.cursojava.mscuentas.model.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{
}
