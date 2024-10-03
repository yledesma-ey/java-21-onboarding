package com.cursojava.mspersonas.rules;

import java.util.ArrayList;
import java.util.List;

public class DecisionMatrix {
    private final List<DecisionRule> rules = new ArrayList<>();

   private static final String CUENTA_PESOS_DOLAR = "Cuenta Dólar y pesos";

    public DecisionMatrix() {
        rules.add(new DecisionRule("Cuenta PESOS", false, null, null, Double.valueOf(0), null));
        rules.add(new DecisionRule(CUENTA_PESOS_DOLAR, true, false, Double.valueOf(1.5), Double.valueOf(200000), null));
        rules.add(new DecisionRule(CUENTA_PESOS_DOLAR, true, false, Double.valueOf(1.0), Double.valueOf(446000), "basic"));
        rules.add(new DecisionRule(CUENTA_PESOS_DOLAR, true, false, Double.valueOf(0.5), Double.valueOf(827000), "gold"));
        rules.add(new DecisionRule(CUENTA_PESOS_DOLAR, true, false, Double.valueOf(0.1), Double.valueOf(999000), "black"));
    }

    public DecisionRule getRule(Boolean renaper, Boolean worldSys, Double veraz, Double salary) {
        if (Boolean.FALSE.equals(renaper)) {
            return rules.stream()
                    .filter(rule -> Boolean.FALSE.equals(renaper))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No rule found"));
        } else {
            return rules.stream()
                    .filter(rule -> rule.matches(renaper, worldSys, veraz, salary))
                    .reduce((a, b) -> b)
                    .orElseThrow(() -> new RuntimeException("No rule found"));
        }
    }
}
