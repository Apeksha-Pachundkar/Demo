package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	//GetMapping makes this api a GET api.
	@GetMapping("/hello")
	public String helloWorldApi() {
		
		return "Hello Boot!!!!";
	}
	
	
}
