package com.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Instructor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

private String name;

private double salary;

@JsonManagedReference
@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name="instructor_course",
           joinColumns = @JoinColumn(name="instructor_id"),
           inverseJoinColumns = @JoinColumn(name="course_id")
		)
private List<Course> course;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public double getSalary() {
	return salary;
}

public void setSalary(double salary) {
	this.salary = salary;
}

public List<Course> getCourse() {
	return course;
}

public void setCourse(List<Course> course) {
	this.course = course;
}

@Override
public String toString() {
	return "Instructor [id=" + id + ", name=" + name + ", salary=" + salary + ", course=" + course + "]";
}


}
