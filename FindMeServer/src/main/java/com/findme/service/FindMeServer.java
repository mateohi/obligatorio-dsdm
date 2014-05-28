package com.findme.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.findme.service.controller", "com.findme.service.dataaccess.hibernatedao"})
@EnableAutoConfiguration
public class FindMeServer {

    public static void main(String[] args) {
        SpringApplication.run(FindMeServer.class, args);
    }
}
