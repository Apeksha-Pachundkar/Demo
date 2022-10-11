package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.StatsDto;
import com.springboot.model.Course;
import com.springboot.model.Instructor;
import com.springboot.repository.CourseRepository;
import com.springboot.repository.InstructorRepository;

@RestController
public class InstructorController {
 
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	 // Insert data in Instructor: create instructor
	@PostMapping("/instructor")
	public Instructor insertInstructor(@RequestBody Instructor instructor) {
		return instructorRepository.save(instructor);
	}
	
	 // Fetch all records
	@GetMapping("/instructor")
	public List<Instructor> getAllInstructors(){
		return instructorRepository.findAll();
	}
	
	
     //delete instructor by id	
	@DeleteMapping("/instructor/{iid}")
	public void deleteInstructor(@PathVariable("iid") Long iid) {
	  instructorRepository.deleteById(iid);	
	}
	
	/*
	 * Add in relationship table
	 */
	@PostMapping("/instructor/course/{iid}/{cid}")
	public Instructor assignInstructorToCourse(@PathVariable("iid") Long iid,@PathVariable("cid") Long cid){
		 Instructor instructor= instructorRepository.getById(iid);
		 List<Course> courses=instructor.getCourse();
		 
		 Course course=courseRepository.getById(cid);
		 courses.add(course);
		 
		 instructor.setCourse(courses);
		return instructorRepository.save(instructor);
	}
	
	
	/*
	 * Fetch list Instructors by courseid
	 */
	@GetMapping("/instructor/course/{cid}")
	public List<Instructor> getInstructorsByCourseId(@PathVariable("cid") Long cid){
		 List<Instructor> list= instructorRepository.findByCourseId(cid);
		return list;
	}
	
	@GetMapping("/instructor/max/salary")
	public StatsDto getMaxInstructorSalary() {
		List<Double[]> list=instructorRepository.getMaxSalary();
		Double[] stats=list.get(0);
		double max=stats[0];
		double min=stats[1];
		double count=stats[2];
		
		StatsDto dto=new StatsDto();
		dto.setCount(count);
		dto.setMaxSalary(max);
		dto.setMinSalary(min);
		
		return dto;
	}
}
