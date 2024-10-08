package com.musinsa.bo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.musinsa.core", "com.musinsa.bo"})
public class BoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoApplication.class, args);
    }

}
