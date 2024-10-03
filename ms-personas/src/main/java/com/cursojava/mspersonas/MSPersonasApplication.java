package com.cursojava.mspersonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MSPersonasApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSPersonasApplication.class, args);
    }
}
