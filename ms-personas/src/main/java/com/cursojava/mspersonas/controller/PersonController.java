package com.cursojava.mspersonas.controller;

import com.cursojava.mspersonas.dto.request.ClientRequest;
import com.cursojava.mspersonas.dto.response.ClientResponse;
import com.cursojava.mspersonas.service.onboarding.AltaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
public class PersonController {

    private final AltaService altaService;

    public PersonController(AltaService altaService) {
        this.altaService = altaService;
    }

    @PostMapping("/altaCliente")
    public ResponseEntity<ClientResponse> customerRegistration(@RequestBody ClientRequest client) {
        return altaService.addClient(client);
    }
}
