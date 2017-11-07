package com.example.demomultiflyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoMultiFlywayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMultiFlywayApplication.class, args);
    }

    @Bean
    FlywayPostProcessor flywayPostProcessor() {
        return new FlywayPostProcessor();
    }
}
