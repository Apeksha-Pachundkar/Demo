package com.hms.receptionistms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.receptionistms.model.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long>{

}
