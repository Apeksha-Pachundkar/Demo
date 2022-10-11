package com.lms.api.controller;

import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.model.Enroll;
import com.lms.api.model.LearningTrack;
import com.lms.api.model.User;

import com.lms.api.repository.EnrollRepository;
import com.lms.api.repository.LearningTrackRepository;
import com.lms.api.repository.UserRepository;

@RestController
public class EnrollController {

   @Autowired
   private LearningTrackRepository learningTrackRepository;
	
   @Autowired
   private UserRepository userRepository;
   

   @Autowired
   private EnrollRepository enrollRepository;
   
	
   /*
	 * enroll API: enroll users in learning track based on Userid & learningTrackId
	 */

	@PostMapping("/enroll/{uid}/{lid}")
	public Enroll addEnroll(@PathVariable("uid") Long uid,@PathVariable("lid") Long lid) {
		LearningTrack l=learningTrackRepository.getById(lid);
		User u=userRepository.getById(uid);
		Enroll enroll=new Enroll();
		enroll.setLearningTrack(l);
		enroll.setUser(u);
		enroll.setEnrolldate(LocalDate.now());
		enroll.setEnddate(LocalDate.now().plusYears(1));
		
		return enrollRepository.save(enroll);
	}	
	
	

}
