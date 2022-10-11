package com.ecom.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
