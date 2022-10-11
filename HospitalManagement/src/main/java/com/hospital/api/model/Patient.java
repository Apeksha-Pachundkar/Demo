package com.hospital.api.model;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//@Column(nullable = false)
	private String patientName;

	/*
	@Column(nullable = false)
	private String bookingTime;*/
	//@Column(nullable = false)
	private LocalDate bookingTime;

	//@Column(nullable = false,unique = true)
	private String mobile;
	
	//@Column(length = 700,nullable = false)
	private String disease;
	
	@OneToOne
	private Doctor doctor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	
	public LocalDate getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDate bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
}
