package com.cursojava.mspersonas.service.onboarding;

import com.cursojava.mspersonas.dto.request.ClientRequest;
import com.cursojava.mspersonas.model.entity.User;
import com.cursojava.mspersonas.model.entity.UserStatus;
import com.cursojava.mspersonas.service.ProductVerificationService;
import com.cursojava.mspersonas.service.UserService;
import com.cursojava.mspersonas.service.UserStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class AltaServiceTest {

    @InjectMocks
    private AltaService altaService;

    @Mock
    private UserService userService;

    @Mock
    private UserStatusService userStatusService;

    @Mock
    private ProductVerificationService productVerificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddClient_NewUser_CreatesUser() {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setDni("12345678");
        clientRequest.setSalary(BigDecimal.valueOf(3000.0));

        User newUser = new User();
        newUser.setId(1L);

        when(userService.findByDni("12345678")).thenReturn(null);
        when(userService.convertClientRequestToUser(clientRequest)).thenReturn(newUser);
        when(userStatusService.getActiveStatus()).thenReturn(null);
        doNothing().when(userService).save(newUser);
        doNothing().when(productVerificationService).determineProduct(any(), any(), any());

        var response = altaService.addClient(clientRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Usuario creado con éxito", response.getBody().getMessage());
    }

    @Test
    void testAddClient_ExistingActiveUser() {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setDni("12345678");

        UserStatus activeStatus = new UserStatus();

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setStatus(activeStatus);
        existingUser.getStatus().setDescription("Activo");

        when(userService.findByDni("12345678")).thenReturn(existingUser);

        var response = altaService.addClient(clientRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Usuario ya se encuentra activo", response.getBody().getMessage());
    }

    @Test
    void testAddClient_ExistingInactiveUser_ActivatesUser() {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setDni("12345678");

        UserStatus inactivoStatus = new UserStatus();

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setStatus(inactivoStatus);
        existingUser.getStatus().setDescription("Inactivo");

        when(userService.findByDni("12345678")).thenReturn(existingUser);
        when(userStatusService.getActiveStatus()).thenReturn(null);
        doNothing().when(userService).save(existingUser);
        doNothing().when(productVerificationService).determineProduct(any(), any(), any());

        var response = altaService.addClient(clientRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals(null, response.getBody().getMessage()); // Adjust according to your implementation
    }

    @Test
    void testAddClient_ExistingBlockedUser() {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setDni("12345678");

        UserStatus bloqueadoStatus = new UserStatus();

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setStatus(bloqueadoStatus);
        existingUser.getStatus().setDescription("Bloqueado");

        when(userService.findByDni("12345678")).thenReturn(existingUser);

        var response = altaService.addClient(clientRequest);

        assertEquals("Usuario bloqueado", response.getBody().getMessage());
    }
}
