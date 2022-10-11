package com.lms.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Enroll {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "DATE")
	private LocalDate enrolldate;
	
	@Column(columnDefinition = "DATE")
	private LocalDate enddate;

	@OneToOne
	private User user;
	
	@OneToOne
	private LearningTrack learningTrack;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	public LocalDate getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(LocalDate enrolldate) {
		this.enrolldate = enrolldate;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LearningTrack getLearningTrack() {
		return learningTrack;
	}

	public void setLearningTrack(LearningTrack learningTrack) {
		this.learningTrack = learningTrack;
	}

	
	
	
}
