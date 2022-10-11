package com.lms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.api.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

	List<Question> getByForumId(Long fid);

	@Query("select q from Question q join q.forum f where f.id=?1")
	List<Question> getAllQuestions(Long fid);


}
