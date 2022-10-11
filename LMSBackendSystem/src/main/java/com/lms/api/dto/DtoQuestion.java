package com.lms.api.dto;

import java.time.LocalDate;

public class DtoQuestion {
private String questionText;
private LocalDate dateOfPost;
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

}
