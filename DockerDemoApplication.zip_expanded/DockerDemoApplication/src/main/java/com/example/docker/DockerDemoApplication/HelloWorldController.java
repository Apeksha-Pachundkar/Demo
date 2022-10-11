package com.example.docker.DockerDemoApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HelloWorldController {

	@GetMapping("/get")
	public String sayHello() {
		return "Hello World from Docker!";
	}
}
