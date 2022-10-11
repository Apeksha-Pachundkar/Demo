package com.hms.receptionistms.model;

import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Guest {

	//Member code, Phone number, Company, Name, Gender, E-mail, Address
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long guestId;
		
		@Column(nullable = false)
		private int memberCode;
		
		@Column(nullable = false, unique = true)
		private String phoneNumber;
		
		@Column(nullable = false)
		private String company;
		
		@Column(nullable = false)
		private String name;
		
		@Column(nullable = false)
		private String gender;
		
		@Column(nullable = false)
		private String email;
		
		@Column(nullable = false)
		private String address;
		

		public Long getGuestId() {
			return guestId;
		}



		public void setGuestId(Long guestId) {
			this.guestId = guestId;
		}



		public int getMemberCode() {
			return memberCode;
		}



		public void setMemberCode(int memberCode) {
			this.memberCode = memberCode;
		}



		public String getPhoneNumber() {
			return phoneNumber;
		}



		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}



		public String getCompany() {
			return company;
		}



		public void setCompany(String company) {
			this.company = company;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public String getGender() {
			return gender;
		}



		public void setGender(String gender) {
			this.gender = gender;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getAddress() {
			return address;
		}



		public void setAddress(String address) {
			this.address = address;
		}



		public Guest(Long guestId, int memberCode, String phoneNumber, String company, String name, String gender,
				String email, String address) {
			super();
			this.guestId = guestId;
			this.memberCode = memberCode;
			this.phoneNumber = phoneNumber;
			this.company = company;
			this.name = name;
			this.gender = gender;
			this.email = email;
			this.address = address;
		}

			
		

	}
	//Member code, Phone number, Company, Name, E-mail, Gender, Address
