package com.hms.reservationms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.hms.reservationms.model.Reservation;
import com.hms.reservationms.repository.ReservationRepository;


@SpringBootTest
class ReservationMsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ReservationRepository reservationRepository;
		
	@Test
	 void testReservation() {
		Reservation r = new Reservation();
		r.setReservationId(9L);
		r.setNoOfChildren(4);
		r.setNoOfAdults(3);
		r.setNoOfNights(2);
		r.setStatus("pqrs");
		r.setCheckInDate(LocalDate.now());
		r.setCheckOutDate(LocalDate.now());
		reservationRepository.save(r);
		assertNotNull(reservationRepository.findById(9L).get());
	}

	/*
	@Test
	 void testGetReservation() {
		List<Reservation> list = reservationRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	*/
	
		
}