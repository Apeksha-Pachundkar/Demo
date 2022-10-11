package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.repository.UserRepository;
import com.springboot.model.User;



@RestController
public class MainController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	public User postUser(@RequestBody User user) {
		String passEncoded =passwordEncoder.encode(user.getPassword());
		user.setPassword(passEncoded);
		return userRepository.save(user);
	}
}
