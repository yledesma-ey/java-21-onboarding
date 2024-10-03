package com.cursojava.mspersonas.repository;

import com.cursojava.mspersonas.model.entity.UserStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusRepository extends CrudRepository<UserStatus, Long> {
    Optional<UserStatus> findByDescription(String description);
}
