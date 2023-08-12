package com.example.int222_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class Int222BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Int222BackendApplication.class, args);
    }

}
