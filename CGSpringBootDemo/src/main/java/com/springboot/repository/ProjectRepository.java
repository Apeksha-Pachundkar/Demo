package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>{

}
