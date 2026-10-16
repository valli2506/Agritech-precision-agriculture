package com.agriculture.irrigation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IrrigationService1Application {

    public static void main(String[] args) {
        SpringApplication.run(
                IrrigationService1Application.class,
                args
        );
    }
}