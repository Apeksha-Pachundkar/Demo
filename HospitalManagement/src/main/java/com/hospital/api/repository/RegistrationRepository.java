package com.hospital.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.api.model.User;

public interface RegistrationRepository extends JpaRepository<User, Long>{

	User findByEmailId(String emailId);

	User findByEmailIdAndPassword(String email, String password);

}
