package com.hms.receptionistms.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.hms.receptionistms.model.Guest;
import com.hms.receptionistms.repository.GuestRepository;
@RestController
public class GuestController {


	@Autowired
	private GuestRepository guestRepository;
	
	
	@PostMapping("/guest")
	public Guest addGuest(@RequestBody Guest guest)  {
		//add guest details to DB
		return guestRepository.save(guest);
	}
	
	
	@GetMapping("/guest")
	public List<Guest> getAllGuest(){
		//get list of all the guest and guest details from DB
		return guestRepository.findAll();
	}
	
	
	@DeleteMapping("/guest/{gid}")		
	public void deleteGuest(@PathVariable("gid") Long gid) {
		//take the id of guest you want to delete
		//delete the guest from DB
		guestRepository.deleteById(gid);
		}
	
	
	@PutMapping("/guest/{gid}")
	public Guest updateGuestDetails(@PathVariable("gid") Long gid,@RequestBody Guest guestNew)
	{
		
		/*
		 * step 1: take id of guest that has to be updated
		 * step 2: go to DB and fetch the record for this id
		 * Step 3: read new guest details 
		 * step 4: update guestdb with new values
		 */
		
		var guestDB = guestRepository.getById(gid);
		
		if(guestNew.getMemberCode() !=0)
			guestDB.setMemberCode(guestNew.getMemberCode());
		if(guestNew.getPhoneNumber() !=null)
			guestDB.setPhoneNumber(guestNew.getPhoneNumber());
		if(guestNew.getCompany() !=null)
			guestDB.setCompany(guestNew.getCompany());
		if(guestNew.getGender() !=null)
			guestDB.setGender(guestNew.getGender());
		if(guestNew.getAddress() !=null)
			guestDB.setAddress(guestNew.getAddress());
		if(guestNew.getName() !=null)
			guestDB.setName(guestNew.getName());
		if(guestNew.getEmail() !=null)
			guestDB.setEmail(guestNew.getEmail());
		
		return guestRepository.save(guestDB);
	}

		
  
}

