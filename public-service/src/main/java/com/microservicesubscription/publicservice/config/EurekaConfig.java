package com.microservicesubscription.publicservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 02/07/22 15:42
 */
@Configuration
public class EurekaConfig {
    @LoadBalanced
    @Bean
    public RestTemplate RestTemplate() {
        return new RestTemplate();
    }
}
