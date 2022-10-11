package com.lms.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.api.model.Forum;

public interface ForumRepository extends JpaRepository<Forum,Long>{

}
