package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.model.Employee;
import com.springboot.model.EmployeeProject;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long>{

	/*Here query is little bit complicated but it has high performance than normal join 
	as it only gives required info 
	*/
	@Query("select e from EmployeeProject ep join ep.project p join ep.employee e where p.id=?1")
	List<Employee> getEmployeeByProject(Long pid);

}
