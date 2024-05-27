package com.mergen.task3.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergen.task3.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TaskConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public TypeReference<Iterable<UserDTO>> typeReference(){
        return new TypeReference<>() {};
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
