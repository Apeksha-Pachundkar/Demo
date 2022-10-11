package com.lms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.model.Author;
import com.lms.api.model.Course;
import com.lms.api.model.LearningTrack;
import com.lms.api.repository.AuthorRepository;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.LearningTrackRepository;

@RestController
public class CourseController {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private CourseRepository courseRepository;
	
   @Autowired
   private LearningTrackRepository learningTrackRepository;

	/*
	 * Create a post api for course
	 */
	
	@PostMapping("/course/{lid}")
	public Course addCourse(@RequestBody Course course,@PathVariable("lid") Long lid) {
		LearningTrack l=learningTrackRepository.getById(lid);
		course.setLearningTrack(l);
		return courseRepository.save(course);
	}	

	

	@GetMapping("/course")
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
	/*
	 * Assign Author to Course:aid,cid 
	 * Add in relationship table
	 */
	
	@PostMapping("/author/course/{aid}/{cid}")
	public Course assignAuthorToCourse(@PathVariable("aid") Long aid,@PathVariable("cid") Long cid){
		
		Course course=courseRepository.getById(cid);
		List<Author> authors=course.getAuthor();
		
		Author author=authorRepository.getById(aid);
		authors.add(author);
		
		course.setAuthor(authors);
		
	
		return courseRepository.save(course);
	}
	
	

	/*
	 * Un-assign Author to Course:aid,cid 
	 */	
	@PutMapping("/author/course/unassign/{aid}/{cid}")
	public Course unassignAuthorToCourse(@PathVariable("aid") Long aid,@PathVariable("cid") Long cid){
		
		Course course=courseRepository.getById(cid);
		List<Author> authors=course.getAuthor();
		
		Author author=authorRepository.getById(aid);
		authors.remove(author);
		
		course.setAuthor(authors);
		
	
		return courseRepository.save(course);
	}
	
	
	
	
	
}




