package com.hms.billingms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.hms.billingms.model.Bill;
import com.hms.billingms.repository.BillRepository;

@SpringBootTest
class BillingMsApplicationTests {

	@Autowired
	private BillRepository billRepository;
	
	@Test
	public void testIssuebill() {
		Bill b = new Bill();
		b.setBillingNo(12L);
		b.setPrice(3000);
		b.setQuantity(1);
		b.setTax(6);
		b.setTotal(3500);
		b.setDate(LocalDate.now());
		
		billRepository.save(b);
		assertNotNull(billRepository.findById(12L).get());
	}
	
	@Test
	public void testGetIssuebill() {
		List<Bill> list = billRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	

}