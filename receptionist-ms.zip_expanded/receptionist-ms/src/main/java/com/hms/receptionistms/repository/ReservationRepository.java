package com.hms.receptionistms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.receptionistms.model.Reservation;



public interface ReservationRepository  extends JpaRepository<Reservation, Long>{

}
