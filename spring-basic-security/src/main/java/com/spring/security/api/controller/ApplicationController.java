package com.spring.security.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth")
public class ApplicationController {

	@GetMapping("/getMsgOwner")
	public String greetingOwner() {
		return "spring security example Owner";
	}
	
	@GetMapping("/getMsgManager")
	public String greetingManager() {
		return "spring security example Manager";
	}
	
	@GetMapping("/getMsgReceptionist")
	public String greetingReceptionist() {
		return "spring security example Receptionist";
	}
	
}
