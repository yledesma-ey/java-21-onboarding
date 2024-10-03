package com.cursojava.mscuentas.repository;

import com.cursojava.mscuentas.model.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    Optional<Currency> findBySymbol(String symbol);
}
