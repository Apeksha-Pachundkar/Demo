package com.hms.reservationms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.reservationms.model.Reservation;

public interface ReservationRepository  extends JpaRepository<Reservation, Long>{

}
