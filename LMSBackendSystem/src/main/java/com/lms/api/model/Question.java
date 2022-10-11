package com.lms.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	@Column(length = 1000, nullable = false)
	private String questionText;
	private int likes;
	private String username;
	
	private LocalDate dateOfPost;
	
	@JsonIgnore
	//@JsonBackReference
	@ManyToMany(mappedBy = "question")
	private List<Forum> forum;
	
	@JsonIgnore
	 //@JsonManagedReference
	@ManyToMany(cascade = CascadeType.ALL)
	 @JoinTable(
			  name="question_answer",
			  joinColumns = @JoinColumn(name="question_id"),
			  inverseJoinColumns = @JoinColumn(name="answer_id")
			)
   
	 
     private List<Answer> answer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public LocalDate getDateOfPost() {
		return dateOfPost;
	}

	public void setDateOfPost(LocalDate dateOfPost) {
		this.dateOfPost = dateOfPost;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Forum> getForum() {
		return forum;
	}

	public void setForum(List<Forum> forum) {
		this.forum = forum;
	}

	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}
	 
}