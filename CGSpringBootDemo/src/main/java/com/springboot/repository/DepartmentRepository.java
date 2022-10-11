package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
