package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Course;
import com.springboot.repository.CourseRepository;
import com.springboot.repository.InstructorRepository;

@RestController
public class CourseController {


	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	 // Insert data in Course: create course
		@PostMapping("/course")
		public Course insertCourse(@RequestBody Course course) {
			return courseRepository.save(course);
		}
		
		
		 // Fetch all records
		@GetMapping("/course")
		public List<Course> getAllCourses(){
			return courseRepository.findAll();
		}
		

	     //delete course by id	
		@DeleteMapping("/course/{cid}")		
		public void deleteCourse(@PathVariable("cid") Long cid) {
		 courseRepository.deleteById(cid);
			}
}
