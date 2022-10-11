package com.hms.managerms.controller;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.hms.managerms.model.Staff;
import com.hms.managerms.repository.StaffRepository;

@RestController
public class StaffController {


	@Autowired
	private StaffRepository staffRepository;
	
	
	// Add staff details (Create)
	@PostMapping("/staff")
	public Staff addStaff( @RequestBody Staff staff) {
		
		return staffRepository.save(staff);
	}
	
	
	// Get Details of staff (Read)
	@GetMapping("/staff")
	public List<Staff> getAllStaff(){
		
		return staffRepository.findAll();
	}
	
	// Delete Staff By Staff Id (Delete)
	@DeleteMapping("/staff/{sid}")		
	public void deleteStaff(@PathVariable("sid") Long sid) {
		 staffRepository.deleteById(sid);
		 
	}
	
	
	// Update Staff By staff id (Update)
	@PutMapping("/staff/update/{sid}")
	public Staff updateStaff(@PathVariable("sid") Long sid, @RequestBody Staff staffNew) {
		
		var staffDB= new Staff();
		 staffDB= staffRepository.getById(sid);
		
		if(staffNew.getEmployeeName() !=null) 
			staffDB.setEmployeeName(staffNew.getEmployeeName());
			
		if(staffNew.getEmployeeAddress() !=null) 
				staffDB.setEmployeeAddress(staffNew.getEmployeeAddress());
		if(staffNew.getNic() !=null) 
					staffDB.setNic(staffNew.getNic());
					
		if(staffNew.getSalary() !=null) 
		  staffDB.setSalary(staffNew.getSalary());
						
		if(staffNew.getAge() !=0) 
			staffDB.setAge(staffNew.getAge());
			
		if(staffNew.getOccupation() !=null) 
			staffDB.setOccupation(staffNew.getOccupation());
		
		if(staffNew.getEmail() !=null) 
			staffDB.setEmail(staffNew.getEmail());
		
		return staffRepository.save(staffDB);
}
}
