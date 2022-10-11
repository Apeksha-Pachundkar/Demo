package com.springboot.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroservicePaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePaymentServiceApplication.class, args);
	}

}
