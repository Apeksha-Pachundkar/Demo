package com.springboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Employee;
import com.springboot.model.EmployeeProject;
import com.springboot.model.Project;
import com.springboot.repository.EmployeeProjectRepository;
import com.springboot.repository.EmployeeRepository;
import com.springboot.repository.ProjectRepository;

@RestController
public class EmployeeProjectController {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeProjectRepository employeeProjectRepository;

	@PostMapping("/employee/project/{eid}/{pid}")
	public EmployeeProject postEmployeeProject(@PathVariable("pid") Long pid, @PathVariable("eid") Long eid) {

		/*
		 * Fetch project object based on @param(pid)
		 */
		Project project = projectRepository.getById(pid);

		/*
		 * Fetch employee object based on @param(eid)
		 */
		Employee employee = employeeRepository.getById(eid);

		/*
		 * create an object of EmployeeProject entity
		 */
		EmployeeProject employeeProject = new EmployeeProject();

		/*
		 * set employee and project object to employeeProject
		 */
		employeeProject.setProject(project);
		employeeProject.setEmployee(employee);

		LocalDate ld = LocalDate.of(2021, 11, 15);
		/*
		 * Convert localDate to Date
		 */
		Date dt = new Date();
		
			dt = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		employeeProject.setWorkingFrom(dt);

		return employeeProjectRepository.save(employeeProject);

	}
	
	@GetMapping("/employeeproject")
	public List<EmployeeProject> getAllEmployeeProject(){
		return employeeProjectRepository.findAll();
	}
	
	@DeleteMapping("/employeeproject/{epid}")
	public void deleteEmployeeProject(@PathVariable("epid") Long epid) {
		employeeProjectRepository.deleteById(epid);
	}
	
	@GetMapping("/employee/project/{pid}")
	public List<Employee> getEmployeeByProject(@PathVariable("pid") Long pid){
		List<Employee> list=employeeProjectRepository.getEmployeeByProject(pid);
		return list;
	}
	
	
}
