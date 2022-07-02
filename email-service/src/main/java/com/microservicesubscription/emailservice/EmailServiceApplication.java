package com.microservicesubscription.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 06:11
 */
@EnableEurekaClient
@SpringBootApplication
public class EmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

}
