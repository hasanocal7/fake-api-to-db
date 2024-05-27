package com.mergen.task3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergen.task3.dto.UserDTO;
import com.mergen.task3.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class Task3Application {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Task3Application.class, args);
	}

	//@PostConstruct
	public void saveUserPostConstruct() throws IOException {
		userService.saveAllUsers();
	}
}
