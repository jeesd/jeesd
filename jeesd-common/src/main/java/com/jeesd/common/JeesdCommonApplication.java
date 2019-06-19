package com.jeesd.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JeesdCommonApplication {

    public static void main(String[] args) {

        SpringApplication.run(JeesdCommonApplication.class, args);
    }

}
