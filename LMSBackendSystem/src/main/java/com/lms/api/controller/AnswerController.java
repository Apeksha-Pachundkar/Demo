package com.lms.api.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.QuestionDto;
import com.lms.api.model.Answer;
import com.lms.api.model.Question;
import com.lms.api.repository.AnswerRepository;
import com.lms.api.repository.QuestionRepository;

@RestController
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	

	//1. add data in answer table
	@PostMapping("/answer")
	public Answer addAnswer(@RequestBody Answer answer,Principal principal) {
		String username=principal.getName();
		answer.setUsername(username);
		
		LocalDate dateOfPost=LocalDate.of(2021, 11,10 );
		//	LocalDate dateOfPost=LocalDate.now();
			answer.setDateOfPost(dateOfPost);
		return answerRepository.save(answer);
	}
	
	   //get data from answer table
		@GetMapping("/answer")
		public List<Answer> getAnswer() {
			return answerRepository.findAll();
		}
	
		/*
		 * 5. GET api for List of Answers based on questionId having following response format
		 * {
			  questionId: ____, 
			  questionText:___,
			  username: _____, 
			  dateOfPost: ____,
			  numberOfAnswer: ____,  
			  answers: [
			      {
			         answerId: ____,
			         answerText: ___,
			         likes: ________,
			         username: ______,
			         dateOfPost: _____
			      }
			   
               ]   
            }
		 */
		@GetMapping("/answer/question/{qid}")
		public QuestionDto getAnswerByQuestionId(@PathVariable("qid") Long qid) {
			List<Answer> answers=answerRepository.getByQuestionId(qid);
			Question question=questionRepository.getById(qid);
			QuestionDto dto=new QuestionDto();
			dto.setQuestionId(qid);
			dto.setQuestionText(question.getQuestionText());
			dto.setUsername(question.getUsername());
			dto.setDateOfPost(question.getDateOfPost());
			dto.setNumberOfAnswer(answers.size());
			
			//sorting the answer list by dateofpost
			List<Answer> sortedList=answers.stream()
                    .sorted(Comparator.comparing(Answer::getDateOfPost).reversed())
                    .collect(Collectors.toList());
			
			dto.setAnswers(sortedList);
			return dto;
		}
	
		/*
		 * 6. POST api for adding answer to the question.
		 */
		//add data in answer table
		@PostMapping("/addAnswer/question/{qid}")
		public Answer addAnswerToQuestion(@RequestBody Answer answer,Principal principal,@PathVariable("qid") Long qid) {
			String username=principal.getName();
			answer.setUsername(username);
		
			LocalDate dateOfPost=LocalDate.of(2021, 11,30 );
			//	LocalDate dateOfPost=LocalDate.now();
				answer.setDateOfPost(dateOfPost);
				
				                   // List<Question> questions=new ArrayList<>();
			Question question=questionRepository.getById(qid);
	                                //questions.add(question);
			
			List<Answer> answerList=question.getAnswer();
			answerList.add(answer);
				question.setAnswer(answerList);
				                   //answer.setQuestion(questions);
			return answerRepository.save(answer);
		}	
		
		/*
		 * 8. PUT api(s) for incrementing the Likes of the question & answer based on their IDs. 
		 */
		@PutMapping("/answer/likes/{aid}")
		public Answer updateAnswerAddLikes(@PathVariable("aid") Long aid) {

		  Answer answerDB=answerRepository.getById(aid);
		   
	
		  int like=answerDB.getLikes()+1;
		  answerDB.setLikes(like);
		   
		
			return answerRepository.save(answerDB);
		}
		
		
		/*
		 * 9. PUT api for question and answer. Only those Users who have posted these questions and answers must be able to edit. 
		 */
		@PutMapping("/answer/{aid}")
		public Answer updateAnswer(Principal principal,@PathVariable("aid") Long aid,@RequestBody Answer answerNew) {
			String username=principal.getName();
			
			//fetch the record from db
		  Answer answerDB=answerRepository.getById(aid);
		  
		  if(answerNew.getAnswerText()!=null)
			  answerDB.setAnswerText(answerNew.getAnswerText());
	
		//  answerDB.setDateOfPost(LocalDate.now());
		  String userOwner=answerDB.getUsername();
			
			if(! username.equalsIgnoreCase(userOwner))
				throw new RuntimeException("user not allowed to edit the review");
			
		
			return answerRepository.save(answerDB);
		}
	
		
		
}
