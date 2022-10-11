package com.hms.receptionistms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceptionistController {


@GetMapping("/receptionisthello")
public String getHello(){
	return "Hello World3";
}
}
