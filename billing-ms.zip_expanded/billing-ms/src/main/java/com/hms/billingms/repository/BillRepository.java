package com.hms.billingms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.billingms.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
