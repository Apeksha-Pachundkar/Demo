package com.hospital.api.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.api.model.Doctor;
import com.hospital.api.model.Patient;
import com.hospital.api.model.Prescription;
import com.hospital.api.repository.DoctorRepository;
import com.hospital.api.repository.PatientRepository;
import com.hospital.api.repository.PrescriptionRepository;


@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepository;
		
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private PrescriptionRepository prescriptionRepository;
		// get data from table
		@GetMapping("/doctor")
		public List<Doctor> getDoctors(){
			return doctorRepository.findAll();
		}
		
		// add data in doctor table
		@PostMapping("/doctor")
		public Doctor addDoctor(@RequestBody Doctor doctor) {
		doctor.setOpdStartTime(LocalTime.of(9, 0));
		doctor.setOpdEndTime(LocalTime.of(3, 0));
			return doctorRepository.save(doctor);
		}

		/*
		 * @CrossOrigin(origins = {"http://localhost:4200/"})
		 * 
		 * @PostMapping("/prescribe/{did}/{pid}") public Prescription
		 * prescribeMedication( @RequestBody Prescription
		 * prescription,@PathVariable("did") Long did, @PathVariable("pid") Long pid){
		 * 
		 * Doctor doctor = doctorRepository.getById(did); Patient patient =
		 * patientRepository.getById(pid);
		 * 
		 * prescription.setDoctor(doctor); prescription.setPatient(patient);
		 * prescription.setPrescriptionText(prescription.getPrescriptionText()); return
		 * prescriptionRepository.save(prescription); }
		 */
		
		@CrossOrigin(origins = {"http://localhost:4200/"})
		@PostMapping("/prescribe/{did}")
		public Prescription prescribeMedication( @RequestBody Prescription prescription,@PathVariable("did") Long did){
			
			Doctor doctor = doctorRepository.getById(did);
			//Patient patient = patientRepository.getById(pid);
		
			prescription.setDoctor(doctor);
		//	prescription.setPatient(patient);
			prescription.setPrescriptionText(prescription.getPrescriptionText());		
			return prescriptionRepository.save(prescription);
		}
		
		@CrossOrigin(origins = {"http://localhost:4200/"})
		@GetMapping("/prescribe")
		public List<Prescription> prescriptionByDoctorId(){
			
		//	Doctor doctor = doctorRepository.getById(did);
			//Patient patient = patientRepository.getById(pid);
		     return prescriptionRepository.findAll();
		}
}
