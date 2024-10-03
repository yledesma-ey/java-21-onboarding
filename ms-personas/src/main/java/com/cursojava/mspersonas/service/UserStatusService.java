package com.cursojava.mspersonas.service;

import com.cursojava.mspersonas.model.entity.UserStatus;
import com.cursojava.mspersonas.repository.UserStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class UserStatusService {

    private final UserStatusRepository userStatusRepository;

    public UserStatusService(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    public UserStatus findById(Long id) {
        return userStatusRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Estado no válido"));
    }

    public UserStatus getActiveStatus() {
        return userStatusRepository.findByDescription("ACTIVO").orElseThrow(() -> new IllegalStateException("Estado activo no encontrado"));
    }


}
