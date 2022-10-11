package com.hospital.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//@Column(length = 500, nullable = false)
	private String prescriptionText;
	
	@OneToOne
	private Patient patient;
	
	@OneToOne
	private Doctor doctor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getPrescriptionText() {
		return prescriptionText;
	}
	

	public void setPrescriptionText(String prescriptionText) {
		this.prescriptionText = prescriptionText;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	
}