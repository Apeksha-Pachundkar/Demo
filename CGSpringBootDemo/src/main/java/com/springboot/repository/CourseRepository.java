package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
