package com.springboot.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceGatewayApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayApp1Application.class, args);
	}

}
