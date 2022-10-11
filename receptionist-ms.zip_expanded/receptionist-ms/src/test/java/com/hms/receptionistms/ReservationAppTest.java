package com.hms.receptionistms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hms.receptionistms.controller.ReservationController;
import com.hms.receptionistms.model.Reservation;
import com.hms.receptionistms.repository.ReservationRepository;


@SpringBootTest(classes = {ReservationAppTest.class})
public class ReservationAppTest {

	@Mock
	ReservationRepository reservationRepository;		//mock the Reservation Repository
	
	@InjectMocks 
	ReservationController reservationController;    // used to invoke the method from this reservationController
	
	public List<Reservation> myreservation;
	
	
	//write a unit test case by mockito-- here the use of mockito-to mock the rxternal dependency
	
	
	//test case for get all reservations
	@Test   //junit cannot excecute until we specify the test method 
	@Order(1)              // if you want to execute this method 1st
	 void test_getAllReservations() {
		
		List<Reservation> myreservations = new ArrayList<Reservation>();
		myreservations.add(new Reservation(1L,2,3,LocalDate.of(2022, 06, 25),LocalDate.of(2022, 06, 27),"aaa",1));
		myreservations.add(new Reservation(2L,2,4,LocalDate.of(2022, 07, 2),LocalDate.of(2022, 07, 5),"abc",3));
		myreservations.add(new Reservation(3L,1,2,LocalDate.of(2022, 10, 19),LocalDate.of(2022, 10, 21),"bbb",2));
				
		when(reservationRepository.findAll()).thenReturn(myreservations);    //Mocking
		
		assertEquals(3,reservationController.getAllReservations().size()); 	//assert to validate
		
	}

	@Test 		//test case for get all reservations when there is no record in DB
	@Order(3)
	  void test_getAllReservations1() {
		
		List<Reservation> myreservations1 = new ArrayList<>();
		
		when(reservationRepository.findAll()).thenReturn(myreservations1);
		
		assertEquals(myreservations1,reservationController.getAllReservations());
		
	}
	
		
	@Test
	@Order(2)
	 void test_makeReservation() {
		
		Reservation reservation = new Reservation(4L,2,3,LocalDate.of(2022, 06, 25),LocalDate.of(2022, 06, 27),"sss",1);
		
		when(reservationRepository.save(reservation)).thenReturn(reservation);
		assertEquals(reservation,reservationController.makeReservation(reservation));
		
	}
	
	
}