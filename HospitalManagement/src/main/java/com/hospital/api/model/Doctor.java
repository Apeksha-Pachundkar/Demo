package com.hospital.api.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String doctorName;

	@Column(nullable = false)
	private String specialization;

	@Column(nullable = false,unique = true)
	private String mobile;
	
	@Column(nullable = false)
	private LocalTime opdStartTime;
	
	@Column(nullable = false)
	private LocalTime opdEndTime;
	
	@Column(nullable = false)
	private int consultingcharge;
	
	@Column(nullable = false,unique = true)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	

	public LocalTime getOpdStartTime() {
		return opdStartTime;
	}

	public void setOpdStartTime(LocalTime opdStartTime) {
		this.opdStartTime = opdStartTime;
	}

	public LocalTime getOpdEndTime() {
		return opdEndTime;
	}

	public void setOpdEndTime(LocalTime opdEndTime) {
		this.opdEndTime = opdEndTime;
	}

	public int getConsultingcharge() {
		return consultingcharge;
	}

	public void setConsultingcharge(int consultingcharge) {
		this.consultingcharge = consultingcharge;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
