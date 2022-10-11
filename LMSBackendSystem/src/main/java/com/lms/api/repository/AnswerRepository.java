package com.lms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.api.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	
	List<Answer> getByQuestionId(Long qid);

	@Query("select a from Answer a join a.question q join q.forum f where f.id=?1")
	List<Answer> getAllAnswersByFId(Long fid);

	

	/*
	 * @Query("select a from Answer a join a.forum f where f.id=?1") 
	 * List<Answer> getByForumId(Long fid);
	 */
	

}
