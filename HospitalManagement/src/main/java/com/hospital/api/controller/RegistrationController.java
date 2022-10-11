package com.hospital.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.api.model.User;
import com.hospital.api.repository.RegistrationRepository;
import com.hospital.api.service.RegistrationService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class RegistrationController {


	@Autowired
	private RegistrationRepository registrationRepository;

	public User saveUser(User user) {
		return registrationRepository.save(user);
	}
	
	public User fetchUserByEmailId(String email) {
		return registrationRepository.findByEmailId(email);
		
	}
	
	public User fetchUserByEmailIdAndPassword(String email, String password) {
		return registrationRepository.findByEmailIdAndPassword(email,password);
	
	}
	
	

	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user)throws Exception {
		String tempEmailId = user.getEmailId();
		if(tempEmailId != null && !"".equals(tempEmailId)) {
			User userObj = fetchUserByEmailId(tempEmailId);
			if(userObj != null) {
				throw new Exception("User with" + tempEmailId + "already exit!!");
			}
		}
		return saveUser(user);
	}
	
	@PostMapping("/login")
	
	public User loginUser(@RequestBody User user) throws Exception{
		String tempEmailId = user.getEmailId();
		String tempPassword = user.getPassword();
		User userObj = null;
		if(tempEmailId != null && tempPassword != null) {
			userObj = fetchUserByEmailIdAndPassword(tempEmailId, tempPassword);
		}
		
		if(userObj == null) {
			throw new Exception("Enter Valid Credentials!!!");
		}
		return userObj;
	}
	
	
	
	
	
	
	/*
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user)throws Exception {
		String tempEmailId = user.getEmailId();
		if(tempEmailId != null && !"".equals(tempEmailId)) {
			User userObj = registrationService.fetchUserByEmailId(tempEmailId);
			if(userObj != null) {
				throw new Exception("User with" + tempEmailId + "already exit!!");
			}
		}
		return registrationService.saveUser(user);
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception{
		String tempEmailId = user.getEmailId();
		String tempPassword = user.getPassword();
		User userObj = null;
		if(tempEmailId != null && tempPassword != null) {
			userObj = registrationService.fetchUserByEmailIdAndPassword(tempEmailId, tempPassword);
		}
		
		if(userObj == null) {
			throw new Exception("Enter Valid Credentials!!!");
		}
		return userObj;
	}
	*/
}
