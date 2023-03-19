package com.niovo.niovokafka.config;

import com.google.gson.Gson;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplateBuilder().build();
    }

    @Bean
    public Gson gson() {

        return new Gson();
    }
}
