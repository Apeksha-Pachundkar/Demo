package com.hms.managerms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private Long staffId;
	@Column(nullable = false)
	private String employeeName;
	@Column(nullable = false)
	private String employeeAddress;
	@Column(nullable = false)
	private String nic;
	
	private Long salary;
	
	private int age;
	
	@Column(nullable = false)
	private String occupation;
	
	@Column(nullable = false)
	private String email;
	
	
	public Staff() {}
	
	
	public Staff(Long staffId, String employeeName, String employeeAddress, String nic, Long salary, int age,
			String occupation, String email) {
		super();
		this.staffId = staffId;
		this.employeeName = employeeName;
		this.employeeAddress = employeeAddress;
		this.nic = nic;
		this.salary = salary;
		this.age = age;
		this.occupation = occupation;
		this.email = email;
	}


	public Long getStaffId() {
		return staffId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}