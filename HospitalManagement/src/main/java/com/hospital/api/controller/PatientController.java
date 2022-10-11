package com.hospital.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.api.model.Doctor;
import com.hospital.api.model.Patient;
import com.hospital.api.repository.DoctorRepository;
import com.hospital.api.repository.PatientRepository;

@RestController
@CrossOrigin(origins = { "http://localhost:4200/" })
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@PostMapping("/patient/{did}")
	@CrossOrigin(origins = { "http://localhost:4200/" })
	public Patient addPatient(@RequestBody Patient patient, @PathVariable("did") Long did) {

		List<Patient> patients = patientRepository.findAll();
		List<Patient> newlist = patients.stream().filter(p -> p.getBookingTime().isEqual(LocalDate.now()))
				               .collect(Collectors.toList());
		int patientcount = newlist.size();

		// System.out.println(patientcount);
		Doctor doctor = doctorRepository.getById(did);
		patient.setDoctor(doctor);

		if (patientcount > 3) {
			throw new RuntimeException("User is not allowed to book appointment");
		}

		return patientRepository.save(patient);
	}

	@GetMapping("/patient")
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@GetMapping("/patient/{did}")
	public List<Patient> getPatientsByDid(@PathVariable("did") Long did) {
		return patientRepository.findByDoctorId(did);
	}

}
