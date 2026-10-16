package com.agriculture.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AnalyticsService1Application {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsService1Application.class, args);
    }
}