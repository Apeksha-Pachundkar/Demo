package com.hms.managerms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

	
@GetMapping("/managerhello")
public String getHello(){
	return "Hello World2";
}
}
