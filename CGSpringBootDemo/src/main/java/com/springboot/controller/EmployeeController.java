package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Department;
import com.springboot.model.Employee;
import com.springboot.repository.DepartmentRepository;
import com.springboot.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	// Insert data in Employee: create employee
	@PostMapping("/employee/{did}")
	public Employee insertEmployee(@RequestBody Employee employee,@PathVariable("did") Long did) {
       Department d= departmentRepository.getById(did);
       employee.setDepartment(d);
		return employeeRepository.save(employee);
	}

	/*
	 * // Fetch all records
	 * 
	 * @GetMapping("/employee") public List<Employee> getAllEmployee() { return
	 * employeeRepository.findAll(); }
	 */
	// Fetch all records
	@GetMapping("/employee") 
	public List<Employee> getAllEmployee(
			@RequestParam(name = "page" ,required = false,defaultValue = "0") Integer page,
			@RequestParam(name = "size" ,required = false,defaultValue ="3")  Integer size	){
		Pageable pageable=PageRequest.of(page, size);
		
		return employeeRepository.findAll(pageable).getContent(); 
		}
	
	// Fetch single employee by Id
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") long id) {
		Employee emp = employeeRepository.getById(id);
		return emp;
	}

	// Update Existing Employee Record
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employeeNew) {
		/*
		 * Step 1: Take the ID of the employee that has to be updated: id 
		 * Step 2: Go to DB and fetch the record for this id: empDB 
		 * Step 3: Read new Employee details/values from user :employeeNew 
		 * Step 4: update empDB with new values: empDB<--employeeNew
		 */

		Employee empDB = employeeRepository.getById(id);// empDB{id:1,___}
      //  long deptid=empDB.getDepartment().getId();//Trying to add department for existing id's
		if (employeeNew.getName() != null)
			empDB.setName(employeeNew.getName());
		
		if (employeeNew.getAge() != 0)
			empDB.setAge(employeeNew.getAge());
		
		if (employeeNew.getCity() != null)
			empDB.setCity(employeeNew.getCity());
		
		if (employeeNew.getSalary() != 0)
			empDB.setSalary(employeeNew.getSalary());
		
		if (employeeNew.getEmail() != null)
			empDB.setEmail(employeeNew.getEmail());
		/*
		 * if(employeeNew.getDepartment().getId()!=null)
		 * empDB.setDepartment(employeeNew.getDepartment());
		 */
		return employeeRepository.save(empDB);
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {
		employeeRepository.deleteById(id);
		
	}
	
	
	/*
	 * Fetch list of employees by city
	 * Passing request param in URL :http://localhost:8181/employee/city?city=london
	 */
	@GetMapping("/employee/city")
	public List<Employee> getEmployeeByCity(@RequestParam("city") String city) {
		List<Employee> list=employeeRepository.findByCity(city);
		return list;
		
	}
	
	/*
	 * Fetch list of employees having salary >70000
	 * Passing request param in URL : http://localhost:8181/employee/salary?salary=70000
	 */
	@GetMapping("/employee/salary")
	public List<Employee> getEmployeeBySalary(@RequestParam("salary") double salary){
		List<Employee> list=employeeRepository.findBySalaryGreaterThan(salary);
		return list;
		
	}
	
	/*
	 * Fetch the employees having age >19
	 * Passing request param in URL : http://localhost:8181/employee/age?age=19
	 */
	@GetMapping("/employee/age")
	public List<Employee> getEmployeeByAge(@RequestParam("age") int age){
		List<Employee> list=employeeRepository.findByAgeGreaterThan(age);
		return list;
		
	}
	
	/*
	 * Fetch list of Employee by department
	 */
	@GetMapping("/employee/department/{did}")
	public List<Employee> getEmployeeByDepartment(@PathVariable("did") Long did) {
		List<Employee> list=employeeRepository.findByDepartmentId(did);
		return list;
	}
	
	
	
	// Update Existing Employee Record Using eid and did
		@PutMapping("/employee/department/{id}/{did}")
		public Employee updateEmployeeDepartment(@PathVariable("id") Long id,
				@PathVariable("did")Long did, @RequestBody Employee employeeNew) {
		
			Employee empDB = employeeRepository.getById(id);// empDB{id:1,___}
	      //Trying to add department for existing id's
			Department d=departmentRepository.getById(did);
			if (employeeNew.getName() != null)
				empDB.setName(employeeNew.getName());
			
			if (employeeNew.getAge() != 0)
				empDB.setAge(employeeNew.getAge());
			
			if (employeeNew.getCity() != null)
				empDB.setCity(employeeNew.getCity());
			
			if (employeeNew.getSalary() != 0)
				empDB.setSalary(employeeNew.getSalary());
			
			if (employeeNew.getEmail() != null)
				empDB.setEmail(employeeNew.getEmail());
			
		    if(d!=null)//check weather department exists or not
			    empDB.setDepartment(d);
			 
			 
			return employeeRepository.save(empDB);
		}
		
		
}

/*
 *  fetch:GETMapping
 *  insert: POST
 *  update: PUT 
 *  delete: DELETE
 */
