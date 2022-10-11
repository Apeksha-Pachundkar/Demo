package com.hms.managerms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hms.managerms.controller.StaffController;
import com.hms.managerms.model.Staff;
import com.hms.managerms.repository.StaffRepository;



@SpringBootTest(classes= {StaffControllerMockitoTests.class})
public class StaffControllerMockitoTests {


	@Mock
	StaffRepository staffRepository;
	
	@InjectMocks
	StaffController staffController;
	
	public List<Staff> myStaffs;
	
	@Test
	@Order(1)
	 void test_getAllStaff() {
		List<Staff> myStaffs= new ArrayList<Staff>();
		
		myStaffs.add(new Staff(1L, "Meena Mane", "Baramati", "55103", 15000L, 26, "Cooker", "meenamane@gmail.com"));
		myStaffs.add(new Staff(2L, "Rohit Pawar", "Pune", "55104", 16000L, 25, "Planner", "rohitpawar@gmail.com"));
		
		// Mocking 
		when(staffRepository.findAll()).thenReturn(myStaffs);
		assertEquals(2, staffController.getAllStaff().size());
		
	}
	
	
	@Test//test case for get all wallets when there is no record in DB 
	 void getStaffTest2() {
		List<Staff> staffList1=new ArrayList<>();
		
		when(staffRepository.findAll()).thenReturn(staffList1);
		
		assertEquals(staffList1, staffController.getAllStaff());
	}
	
	@Test
	@Order(2)
	 void test_addStaff() {
		
		Staff staff=new Staff(3L, "Rohan Khalate", "Pune", "55105", 17000L, 26, "Planner", "rohan@gmail.com");
		when(staffRepository.save(staff)).thenReturn(staff);
		assertEquals(staff, staffController.addStaff(staff));
	}
	
	@Test
	@Order(3)
	 void test_updateStaff() {
		
		long staffId=3L;
		Staff staff=new Staff(3L, "Rohan Khalate", "Pune", "55105", 17000L, 26, "Planner", "rohan@gmail.com");
		when( staffRepository.getById(staffId)).thenReturn(staff);
		when(staffController.updateStaff(staffId, staff)).thenReturn(staff);
		Staff updatedStaffDetails= staffController.updateStaff(staffId, staff);
		assertEquals(staffId, updatedStaffDetails.getStaffId());
		
		if(updatedStaffDetails.getEmployeeName() != null)
		assertEquals("Rohan Khalate", updatedStaffDetails.getEmployeeName());
		
		if(updatedStaffDetails.getEmployeeAddress() != null)
		assertEquals("Pune", updatedStaffDetails.getEmployeeAddress());
		
		if(updatedStaffDetails.getNic() != null)
		assertEquals("55105", updatedStaffDetails.getNic());
		
		if(updatedStaffDetails.getSalary() != null)
		assertEquals(17000L, updatedStaffDetails.getSalary());
		
		if(updatedStaffDetails.getAge() != 0)
		assertEquals(26, updatedStaffDetails.getAge());
		
		if(updatedStaffDetails.getOccupation() != null)
		assertEquals("Planner", updatedStaffDetails.getOccupation());
		
		if(updatedStaffDetails.getEmail() != null)
		assertEquals("rohan@gmail.com", updatedStaffDetails.getEmail());
		
				
		assertEquals(staff, staffController.updateStaff(staffId, staff));
	}
	
	
	@Test
	@Order(4)
       void test_deleteStaff() {
		
		Staff staff=new Staff(3L, "Rohan Khalate", "Pune", "55105", 17000L, 26, "Planner", "rohan@gmail.com");
		long sid=staff.getStaffId();
		staffController.deleteStaff(sid);
		
		verify(staffRepository,times(1)).deleteById(sid);
	}
	
	
	
	
	
}
