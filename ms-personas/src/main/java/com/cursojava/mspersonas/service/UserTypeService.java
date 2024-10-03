package com.cursojava.mspersonas.service;

import com.cursojava.mspersonas.model.entity.UserType;
import com.cursojava.mspersonas.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType findById(Long id) {
        return userTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tipo no válido"));
    }
}
