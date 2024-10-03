package com.cursojava.mspersonas.repository;

import com.cursojava.mspersonas.model.entity.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends CrudRepository<UserType, Long> {
}
