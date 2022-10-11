package com.hms.receptionistms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.receptionistms.model.Reservation;
import com.hms.receptionistms.repository.ReservationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
public class ReservationController {


@GetMapping("/reservationhello")
public String getHello(){
	return "Hello World4";
}

@Autowired
private ReservationRepository reservationRepository;

@PostMapping("/reservation")	//To make the reservation
public Reservation makeReservation(@RequestBody Reservation reservation) {
	
	return reservationRepository.save(reservation);
}

@GetMapping("/reservation")		//To get all reservations
public List<Reservation> getAllReservations(){
	return reservationRepository.findAll();
}
}
