package com.example.casestudy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Java Case Study"))
public class CasestudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasestudyApplication.class, args);
	}

}
