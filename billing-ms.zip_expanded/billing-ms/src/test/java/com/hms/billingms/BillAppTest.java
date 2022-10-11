package com.hms.billingms;
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

import com.hms.billingms.controller.BillController;
import com.hms.billingms.model.Bill;
import com.hms.billingms.repository.BillRepository;



@SpringBootTest(classes = {BillAppTest.class})
public class BillAppTest {


	@Mock
	BillRepository billRepository;			//mock the Reservation Repository
	
	@InjectMocks
	BillController billController;		// used to invoke the method from this reservationController
	
	public List<Bill> mybill;
	
	//write a unit test case by mockito-- here the use of mockito-to mock the rxternal dependency
	
	@Test   //junit cannot excecute until we specify the test method 
	@Order(1)              // if you want to execute this method 1st
	
	public void test_getAllBills() {
		List<Bill> mybill = new ArrayList<Bill>();
		mybill.add(new Bill(1L,2,3000,5,LocalDate.of(2022, 06, 25),3500));
		mybill.add(new Bill(2L,2,3500,5,LocalDate.of(2022, 06, 28),400));
		
		when(billRepository.findAll()).thenReturn(mybill);		//Mocking
		assertEquals(2,billController.getAllBills().size());	//assert to validate
		
	}

	
	@Test   
	@Order(2)            
	
	public void test_issueBill() {
		
		Bill bill = new Bill(3L,2,4000,5,LocalDate.of(2022, 07, 25),4500);
		
		when(billRepository.save(bill)).thenReturn(bill);
		assertEquals(bill,billController.issueBill(bill));
		
	}

}