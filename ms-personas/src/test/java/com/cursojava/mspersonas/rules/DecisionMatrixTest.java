package com.cursojava.mspersonas.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecisionMatrixTest {
    @Test
    void getRule_GetRuleMatching() {
        DecisionMatrix decisionMatrix = new DecisionMatrix();

        DecisionRule expectedRule = new DecisionRule("Cuenta Dólar y pesos", true, false, 1.5, 200000.0, null);
        DecisionRule actualRule = decisionMatrix.getRule(true, false, 1.1, 300000.0);

        assertEquals(expectedRule.getProduct(), actualRule.getProduct());
        assertEquals(expectedRule.getTarjeta(), actualRule.getTarjeta());
    }

    @Test
    void getRule_GetRuleMatchingSalary() {
        DecisionMatrix decisionMatrix = new DecisionMatrix();

        DecisionRule expectedRule = new DecisionRule("Cuenta Dólar y pesos", true, false, 0.1, 999000.0, "black");
        DecisionRule actualRule = decisionMatrix.getRule(true, false, 0.1, 1000000.0);

        assertEquals(expectedRule.getProduct(), actualRule.getProduct());
        assertEquals(expectedRule.getTarjeta(), actualRule.getTarjeta());
    }

    @Test
    void getRule_NoRuleFound() {
        DecisionMatrix decisionMatrix = new DecisionMatrix();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            decisionMatrix.getRule(true, true, 0.1, 1000000.0);
        });

        String expectedMessage = "No rule found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getRule_AnotherMatchingRule() {
        DecisionMatrix decisionMatrix = new DecisionMatrix();

        DecisionRule expectedRule = (new DecisionRule("Cuenta PESOS", false, null, null, Double.valueOf(0), null));
        DecisionRule actualRule = decisionMatrix.getRule(false, false, 0.5, 900000.0);

        assertEquals(expectedRule.getProduct(), actualRule.getProduct());
        assertEquals(expectedRule.getTarjeta(), actualRule.getTarjeta());
    }
}
