package com.cursojava.mspersonas.service;

import com.cursojava.mspersonas.dto.request.ClientRequest;
import com.cursojava.mspersonas.model.entity.User;
import com.cursojava.mspersonas.model.entity.UserStatus;
import com.cursojava.mspersonas.model.entity.UserType;
import com.cursojava.mspersonas.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserTypeService userTypeService;

    private final UserStatusService userStatusService;

    public UserService(UserRepository userRepository, UserTypeService userTypeService, UserStatusService userStatusService) {
        this.userRepository = userRepository;
        this.userTypeService = userTypeService;
        this.userStatusService = userStatusService;
    }

    public User findByDni(String dni) {
        return userRepository.findByDni(dni).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User convertClientRequestToUser(ClientRequest clientRequest) {
        UserType type = userTypeService.findById(clientRequest.getUserType());

        UserStatus status = userStatusService.getActiveStatus();

        return User.builder()
                .dni(clientRequest.getDni())
                .name(clientRequest.getName())
                .lastName(clientRequest.getLastName())
                .status(status)
                .type(type)
                .build();
    }
}
