package com.cursojava.mstarjetas.repository;

import com.cursojava.mstarjetas.model.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
}
