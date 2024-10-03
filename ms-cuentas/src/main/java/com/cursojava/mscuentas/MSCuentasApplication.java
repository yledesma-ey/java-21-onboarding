package com.cursojava.mscuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MSCuentasApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSCuentasApplication.class, args);
    }
}
