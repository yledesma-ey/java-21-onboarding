package com.cursojava.mspersonas.service.onboarding;

import com.cursojava.mspersonas.dto.request.ClientRequest;
import com.cursojava.mspersonas.dto.response.ClientResponse;
import com.cursojava.mspersonas.model.entity.User;
import com.cursojava.mspersonas.model.error.ErrorResponse;
import com.cursojava.mspersonas.service.ProductVerificationService;
import com.cursojava.mspersonas.service.UserStatusService;
import com.cursojava.mspersonas.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AltaService {

    private final UserService userService;

    private final UserStatusService userStatusService;

    private final ProductVerificationService productVerificationService;

    public AltaService(UserService userService, UserStatusService userStatusService, ProductVerificationService productVerificationService) {
        this.userService = userService;
        this.userStatusService = userStatusService;
        this.productVerificationService = productVerificationService;
    }

    public ResponseEntity<ClientResponse> addClient(ClientRequest client) {
        ClientResponse clientResponse = saveClient(client);
        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }

    private ClientResponse saveClient(ClientRequest userRequest) {
        var user = userService.findByDni(userRequest.getDni());

        if (user == null) {
            User newUser = userService.convertClientRequestToUser(userRequest);
            userService.save(newUser);
            productVerificationService.determineProduct(newUser.getId(), newUser.getDni(), userRequest.getSalary());
            return ClientResponse.builder()
                    .id(newUser.getId())
                    .message("Usuario creado con éxito")
                    .build();
        } else {
            var userStatus = user.getStatus().getDescription();
            String message = null;
            switch (userStatus) {
                case "Activo" -> {
                    message = "Usuario ya se encuentra activo";
                    log.info(message);
                }
                case "Inactivo" -> {
                    user.setStatus(userStatusService.getActiveStatus());
                    userService.save(user);
                    productVerificationService.determineProduct(user.getId(), user.getDni(), userRequest.getSalary());
                }
                case "Bloqueado" -> {
                    message = "Usuario bloqueado";
                    log.error(message);
                }
                default -> throw new IllegalStateException("Unexpected value: " + userStatus);
            }
            return ClientResponse.builder()
                    .id(user.getId())
                    .message(message)
                    .build();
        }
    }
}
