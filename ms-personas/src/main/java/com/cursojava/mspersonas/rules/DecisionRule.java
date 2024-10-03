package com.cursojava.mspersonas.rules;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class DecisionRule {
    private final Boolean renaper;
    private final Boolean worldSys;
    private final Double veraz;
    private final Double salary;
    private final String product;
    private final String tarjeta;

    public DecisionRule(String product, Boolean renaper, Boolean worldSys, Double veraz, Double salary, String tarjeta) {
        this.renaper = renaper;
        this.worldSys = worldSys;
        this.veraz = veraz;
        this.salary = salary;
        this.product = product;
        this.tarjeta = tarjeta;
    }

    public Boolean matches(Boolean renaper, Boolean worldSys, Double veraz, Double salary) {
        return Objects.equals(this.renaper, renaper) &&
                Objects.equals(this.worldSys, worldSys) &&
                (this.veraz == null || veraz == null || this.veraz >= veraz) &&
                (this.salary == null || salary == null || this.salary <= salary);
    }

    @Override
    public String toString() {
        return "DecisionRule{" +
                "product='" + product + '\'' +
                ", renaper=" + renaper +
                ", worldSys=" + worldSys +
                ", veraz=" + veraz +
                ", salary=" + salary +
                ", tarjeta='" + tarjeta + '\'' +
                '}';
    }
}
