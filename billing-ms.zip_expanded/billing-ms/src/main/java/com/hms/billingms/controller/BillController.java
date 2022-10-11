package com.hms.billingms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.hms.billingms.model.Bill;
import com.hms.billingms.repository.BillRepository;


@RestController
public class BillController {

	@Autowired
	private BillRepository billRepository;
	
	
	
	@GetMapping("/billhello")  //
	public String getHello() {
		return "hello world 6";
	}
	
	@PostMapping("/issuebill")   //To issue bill
	public Bill issueBill(@RequestBody Bill bill) {
		
		return billRepository.save(bill);
	}

	@GetMapping("/issuebill") //To get all the bills
	public List<Bill> getAllBills() {
		
		return billRepository.findAll();
	}

	
}
