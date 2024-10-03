package com.cursojava.mspersonas.service;

import com.cursojava.mspersonas.client.service.RenaperService;
import com.cursojava.mspersonas.client.service.VerazService;
import com.cursojava.mspersonas.client.service.WorldsysService;
import com.cursojava.mspersonas.dto.AccountMessage;
import com.cursojava.mspersonas.dto.CardMessage;
import com.cursojava.mspersonas.rules.DecisionMatrix;
import com.cursojava.mspersonas.rules.DecisionRule;
import com.cursojava.mspersonas.service.kafka.KafkaSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProductVerificationServiceTest {

    @InjectMocks
    private ProductVerificationService productVerificationService;

    @Mock
    private RenaperService renaperService;

    @Mock
    private WorldsysService worldsysService;

    @Mock
    private VerazService verazService;

    @Mock
    private DecisionMatrix decisionMatrix;

    @Mock
    private KafkaSender kafkaSender;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDetermineProduct_WhenAllServicesReturnValidValues() throws Exception {
        // Arrange
        Long persnum = 12345L;
        String dni = "12345678";
        BigDecimal grossSalary = BigDecimal.valueOf(10000.0);

        when(renaperService.isAuthorized(dni)).thenReturn(CompletableFuture.completedFuture(true));
        when(worldsysService.isTerrorist(dni)).thenReturn(CompletableFuture.completedFuture(false));
        when(verazService.getScore(dni)).thenReturn(CompletableFuture.completedFuture(800.0));

        DecisionRule decisionRule = new DecisionRule("Cuenta Dólar y pesos", true, false, 1.0, 300000.00, "Tarjeta basic");
        when(decisionMatrix.getRule(true, false, 800.0, 10000.0)).thenReturn(decisionRule);

        when(objectMapper.writeValueAsString(any(AccountMessage.class))).thenReturn("accountMessage");
        when(objectMapper.writeValueAsString(any(CardMessage.class))).thenReturn("cardMessage");
        when(kafkaSender.sendMessage(eq("cuenta-topic"), eq("key"), eq("accountMessage"))).thenReturn(CompletableFuture.completedFuture(null));
        when(kafkaSender.sendMessage(eq("tarjeta-topic"), eq("key"), eq("cardMessage"))).thenReturn(CompletableFuture.completedFuture(null));
        when(decisionMatrix.getRule(true, false, 800.0, 10000.0)).thenReturn(decisionRule);

        when(objectMapper.writeValueAsString(any(AccountMessage.class))).thenReturn("accountMessage");
        when(objectMapper.writeValueAsString(any(CardMessage.class))).thenReturn("cardMessage");
        when(kafkaSender.sendMessage(eq("cuenta-topic"), eq("key"), eq("accountMessage"))).thenReturn(CompletableFuture.completedFuture(null));
        when(kafkaSender.sendMessage(eq("tarjeta-topic"), eq("key"), eq("cardMessage"))).thenReturn(CompletableFuture.completedFuture(null));

        // Act
        productVerificationService.determineProduct(persnum, dni, grossSalary);

        verify(renaperService, times(1)).isAuthorized(dni);
        verify(worldsysService, times(1)).isTerrorist(dni);
        verify(verazService, times(1)).getScore(dni);
    }

    @Test
    void testDetermineProduct_WhenNoMatchingRule() {
        Long persnum = 12345L;
        String dni = "12345678";
        BigDecimal grossSalary = BigDecimal.valueOf(100);

        when(renaperService.isAuthorized(dni)).thenReturn(CompletableFuture.completedFuture(true));
        when(worldsysService.isTerrorist(dni)).thenReturn(CompletableFuture.completedFuture(false));
        when(verazService.getScore(dni)).thenReturn(CompletableFuture.completedFuture(0.1));

        when(decisionMatrix.getRule(true, false, 0.1, grossSalary.doubleValue())).thenReturn(null);

        productVerificationService.determineProduct(persnum, dni, grossSalary);

        verify(renaperService, times(1)).isAuthorized(dni);
        verify(worldsysService, times(1)).isTerrorist(dni);
        verify(verazService, times(1)).getScore(dni);
        verify(kafkaSender, never()).sendMessage(anyString(), anyString(), anyString());
    }

    @Test
    void testDetermineProduct_WhenExceptionThrown() {
        Long persnum = 12345L;
        String dni = "12345678";
        BigDecimal grossSalary = BigDecimal.valueOf(10000.0);

        when(renaperService.isAuthorized(dni)).thenReturn(CompletableFuture.failedFuture(new RuntimeException("Service error")));
        when(worldsysService.isTerrorist(dni)).thenReturn(CompletableFuture.completedFuture(false));
        when(verazService.getScore(dni)).thenReturn(CompletableFuture.completedFuture(800.0));

        productVerificationService.determineProduct(persnum, dni, grossSalary);

        verify(renaperService, times(1)).isAuthorized(dni);
        verify(worldsysService, times(1)).isTerrorist(dni);
        verify(verazService, times(1)).getScore(dni);
        verify(decisionMatrix, never()).getRule(anyBoolean(), anyBoolean(), anyDouble(), anyDouble());
        verify(kafkaSender, never()).sendMessage(anyString(), anyString(), anyString());
    }
}
