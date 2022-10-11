package com.hms.receptionistms;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.hms.receptionistms.controller.GuestController;
import com.hms.receptionistms.model.Guest;
import com.hms.receptionistms.repository.GuestRepository;

@SpringBootTest(classes= {GuestAppTest.class})
public class GuestAppTest {

	@Mock
	GuestRepository guestrepo;  
	
	@InjectMocks
	GuestController guestcontroller;
	
	public List<Guest> myGuests;

	@Test
	@Order(1)
     void testgetGuest() {
		//Member code, Phone number(str), Company, Name, Gender, E-mail, Address
		
		List<Guest> myGuests = new ArrayList<Guest>();
		myGuests.add(new Guest(1L, 4563, "4567389546","abcd","harry","male","harry@gmail.com","street no.4"));
		myGuests.add(new Guest(2L, 4564, "7367389546","xyz","ron","male","ron@gmail.com","street no.5"));
		myGuests.add(new Guest(3L, 4565, "7367389533","pqr","hermoine","female","hermoine@gmail.com","street no.6"));
		
		//mocking statement: mocking the controller and the values
		when(guestrepo.findAll()).thenReturn(myGuests);
		assertEquals(3,guestcontroller.getAllGuest().size());      //(expected value, actual value)
	}
	
	@Test
	@Order(2)
	 void testaddGuest() {
		//create new object
		Guest guest = new Guest(1L, 4563, "4567389546","abcd","harry","male","harry@gmail.com","street no.4");
		
		//test whether same record is returned or not
		when(guestrepo.save(guest)).thenReturn(guest);
		assertEquals(guest,guestcontroller.addGuest(guest));
		
	}
	
	@Test
	@Order(3)
	 void testupdateGuest() {
		
		//take the record you want to update
		Guest guest = new Guest(1L, 4563, "45673889546","abcd","harry","male","harry@gmail.com","street no.4");
		//pass guestid with data
		long GuestId=1L;
		
		when(guestrepo.getById(GuestId)).thenReturn(guest);
		when(guestcontroller.updateGuestDetails(GuestId, guest)).thenReturn(guest);
		
		//call the update method to get guest id and body to update
		//save the response in the guestnew variable
		Guest guestnew = guestcontroller.updateGuestDetails(GuestId, guest);
	
		assertEquals(GuestId, guestnew.getGuestId());
		assertEquals("45673889546",guestnew.getPhoneNumber());
		assertEquals("harry",guestnew.getName());
		assertEquals("harry@gmail.com",guestnew.getEmail());
		
		assertEquals(guest,guestcontroller.updateGuestDetails(GuestId,guest)); 
	}
	
	
	@Test
	@Order(4)
	 void testdeleteGuest() {
		//take the id of the guest you want to delete
		Guest guest = new Guest(1L, 4563, "4567389546","abcd","harry","male","harry@gmail.com","street no.4");
		long guestId=guest.getGuestId();
		guestcontroller.deleteGuest(guestId);
		verify(guestrepo,times(1)).deleteById(guestId);
		
	}
	
	
	//test case for get all guests when there is no record in DB 		 
	@Test 
	@Order(5)
	 void getGuestTest() {
		List<Guest> guest=new ArrayList<>();

		when(guestrepo.findAll()).thenReturn(guest);

		assertEquals(guest, guestcontroller.getAllGuest());
	}
	
}
