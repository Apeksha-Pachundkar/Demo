package com.hms.reservationms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 //in realtime application put it on the config class
//@EnableWebMvc
public class ReservationMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationMsApplication.class, args);
	}

}
