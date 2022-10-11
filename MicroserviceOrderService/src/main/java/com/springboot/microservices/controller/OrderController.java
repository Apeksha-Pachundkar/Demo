package com.springboot.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@GetMapping("/hello")
	public String helloApi() {
		return "Hello from Order Service";
	}
}
