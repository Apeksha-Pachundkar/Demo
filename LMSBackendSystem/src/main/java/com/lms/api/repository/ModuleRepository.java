package com.lms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.api.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Long>{

	@Query("select m from Module m where m.course.id=?1")
	List<Module> getByCourseId(Long cid);

}
