package com.hospital.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.api.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
