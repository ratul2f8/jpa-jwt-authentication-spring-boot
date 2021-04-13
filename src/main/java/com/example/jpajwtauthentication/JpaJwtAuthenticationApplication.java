package com.example.jpajwtauthentication;

import com.example.jpajwtauthentication.dto.UserCreationRequest;
import com.example.jpajwtauthentication.services.AppUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaJwtAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaJwtAuthenticationApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(AppUserDetailsService appUserDetailsService){
		return args -> {
			appUserDetailsService.createUser(new UserCreationRequest("John Doe", "john@doe.com","password"));
		};
	}
}
