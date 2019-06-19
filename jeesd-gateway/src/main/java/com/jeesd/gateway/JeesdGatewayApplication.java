package com.jeesd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
//@EnableZuulProxy
//@EnableOAuth2Sso
public class JeesdGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(JeesdGatewayApplication.class, args);
    }

}
