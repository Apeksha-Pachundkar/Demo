package com.springboot.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroserviceServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceServiceDiscoveryApplication.class, args);
	}

}
