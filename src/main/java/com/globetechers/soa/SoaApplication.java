package com.globetechers.soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoaApplication {

	public static void main(String[] args) {
        SpringApplication.run(SoaApplication.class, args);

        System.out.println("==============================================");
        System.out.println(" GlobeTechers SOA iniciado com sucesso! 🚀");
        System.out.println(" Swagger UI:    http://localhost:8080/swagger-ui.html");
        System.out.println(" H2 Console:    http://localhost:8080/h2-console");
        System.out.println("==============================================");
	}

}
