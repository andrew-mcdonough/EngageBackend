package com.engage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.engage")
public class EngageBackendMysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(EngageBackendMysqlApplication.class, args);
    }
}
