package com.example.lonua.config;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortoneConfig {

    @Value("${imp.imp-key}")
    private String apiKey;
    @Value("${imp.imp-secret}")
    private String apiSecretKey;

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, apiSecretKey);
    }
}
