package com.hospital.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.api.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

	List<Patient> findByDoctorId(Long did);

}
