package com.hospital.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.api.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{

	Prescription getByDoctorId(Long did);

}
