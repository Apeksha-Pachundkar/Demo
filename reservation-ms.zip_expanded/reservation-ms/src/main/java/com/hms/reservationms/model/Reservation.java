package com.hms.reservationms.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Reservation table

@Entity
public class Reservation {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private Long reservationId;
		
		@Column(nullable = false)
		private int noOfChildren;
		
		@Column(nullable = false)
		private int noOfAdults;
		
		@Column(nullable = false)
		private LocalDate checkInDate;
		
		@Column(nullable = false)
		private LocalDate checkOutDate;
		
		@Column(nullable = false)
		private String status;
		
		@Column(nullable = false)
		private int noOfNights;
		
	
		
		//Constructor with fields
		public Reservation(Long reservationId, int noOfChildren, int noOfAdults, LocalDate checkInDate,
				LocalDate checkOutDate, String status, int noOfNights) {
			super();
			this.reservationId = reservationId;
			this.noOfChildren = noOfChildren;
			this.noOfAdults = noOfAdults;
			this.checkInDate = checkInDate;
			this.checkOutDate = checkOutDate;
			this.status = status;
			this.noOfNights = noOfNights;
		}

		//default constructor
		public Reservation() {
			super();
			
		}

		//getters,setters

		public Long getReservationId() {
			return reservationId;
		}
	

		public void setReservationId(Long reservationId) {
			this.reservationId = reservationId;
		}

		public int getNoOfChildren() {
			return noOfChildren;
		}

		public void setNoOfChildren(int noOfChildren) {
			this.noOfChildren = noOfChildren;
		}

		public int getNoOfAdults() {
			return noOfAdults;
		}

		public void setNoOfAdults(int noOfAdults) {
			this.noOfAdults = noOfAdults;
		}

		public LocalDate getCheckInDate() {
			return checkInDate;
		}

		public void setCheckInDate(LocalDate checkInDate) {
			this.checkInDate = checkInDate;
		}

		public LocalDate getCheckOutDate() {
			return checkOutDate;
		}

		public void setCheckOutDate(LocalDate checkOutDate) {
			this.checkOutDate = checkOutDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getNoOfNights() {
			return noOfNights;
		}

		public void setNoOfNights(int noOfNights) {
			this.noOfNights = noOfNights;
		}
		
		
}
