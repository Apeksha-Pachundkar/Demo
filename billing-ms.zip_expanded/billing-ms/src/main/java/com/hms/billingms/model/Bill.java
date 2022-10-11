package com.hms.billingms.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Bill table
@Entity
public class Bill {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private Long billingNo;
		
		@Column(nullable = false)
		private int quantity;
		
		@Column(nullable = false)
		private int price;
		
		@Column(nullable = false)
		private int tax;
		
		@Column(nullable = false)
		private LocalDate Date;
		
		@Column(nullable = false)
		private int total;
	
		//default constructor
		public Bill() {
			super();
			// TODO Auto-generated constructor stub
		}

		//Constructor with fields
		public Bill(Long billingNo, int quantity, int price, int tax, LocalDate date, int total) {
			super();
			this.billingNo = billingNo;
			this.quantity = quantity;
			this.price = price;
			this.tax = tax;
			Date = date;
			this.total = total;
		}

		//getters,setters
		public Long getBillingNo() {
			return billingNo;
		}

		public void setBillingNo(Long billingNo) {
			this.billingNo = billingNo;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public int getTax() {
			return tax;
		}

		public void setTax(int tax) {
			this.tax = tax;
		}

		public LocalDate getDate() {
			return Date;
		}

		public void setDate(LocalDate date) {
			Date = date;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
		

}
