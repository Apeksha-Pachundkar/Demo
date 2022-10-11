package com.lms.api.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
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

import com.lms.api.dto.ForumDto;
import com.lms.api.model.Answer;
import com.lms.api.model.Forum;
import com.lms.api.model.Question;
import com.lms.api.repository.AnswerRepository;
import com.lms.api.repository.ForumRepository;
import com.lms.api.repository.QuestionRepository;

@RestController
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private ForumRepository forumRepository;

	// add data in question table
	@PostMapping("/question")
	public Question addQuestion(@RequestBody Question question, Principal principal) {
		String username = principal.getName();
		question.setUsername(username);
		LocalDate dateOfPost = LocalDate.of(2021, 11, 5);
		// LocalDate dateOfPost=LocalDate.now();
		question.setDateOfPost(dateOfPost);
		return questionRepository.save(question);
	}

	// get data from question table
	@GetMapping("/question")
	public List<Question> getQuestion() {
		return questionRepository.findAll();
	}

	// add Answer To Question
	@PostMapping("/question/answer/{qid}/{aid}")
	public Question addAnswerForQuestion(@PathVariable("qid") Long qid, @PathVariable("aid") Long aid) {

		Question question = questionRepository.getById(qid);
		List<Answer> answers = question.getAnswer();

		Answer answer = answerRepository.getById(aid);
		answers.add(answer);

		question.setAnswer(answers);

		return questionRepository.save(question);
	}

	/*
	 * 4. Create a GET API, to display list of Questions based on topicID.
	 * 
	 * response format: 
	 * { 
	 * topicID: __, 
	 * topic: ___, 
	 * questions: [
	 *        { id: ___, 
	 *          text: __,
	 *          likes: __, 
	 *          username: __, 
	 *          numberAnswers: __ 
	 *         }, 
	 *         { id:____, 
	 *           text: __, 
	 *           likes: __,
	 *           username: __, 
	 *           numberAnswers: __ 
	 *          }
	 *           ],
	 *   }
	 */
	@GetMapping("/forum/question/{fid}")
	public ForumDto getQuestionsByForumId(@PathVariable("fid") Long fid) {
		
		// fetch the topic details based on topicId
		Forum forum = forumRepository.getById(fid);

		// fetch the list of questions based on topicId
		List<Question> questionsList = questionRepository.getByForumId(fid);

		ForumDto dto = new ForumDto();
		List<ForumDto.QuestionDto4> listDto = new ArrayList<>();

		dto.setTopicID(fid);
		dto.setTopic(forum.getTopic());

		questionsList.stream().forEach(q -> {
			ForumDto.QuestionDto4 questionDto = new ForumDto.QuestionDto4();

			// find list of answers for each questions
			List<Answer> answerList = answerRepository.getByQuestionId(q.getId());

			questionDto.setQuestionsId(q.getId());
			questionDto.setQuestionsText(q.getQuestionText());
			questionDto.setQuestionsLikes(q.getLikes());
			questionDto.setQuestionsUsername(q.getUsername());
            questionDto.setQuestionPostDate(q.getDateOfPost());
			// count the size of answerList
			questionDto.setNumberAnswers(answerList.size());

			listDto.add(questionDto);

		});
		
		  //sorting the question list by dateofpost 
		  List<ForumDto.QuestionDto4> sortedQuestionList=listDto.stream()
		              .sorted(Comparator.comparing(ForumDto.QuestionDto4::getQuestionPostDate).reversed())
		              .collect(Collectors.toList());
		 
		dto.setQuestions(sortedQuestionList);
		return dto;
	}

	/*
	 * 7. POST api for adding question to the forum
	 */
	//add data in question table
	@PostMapping("/addQuestion/forum/{fid}")
	public Question addQuestionToForum(@RequestBody Question question,Principal principal,@PathVariable("fid") Long fid) {
		String username=principal.getName();
		question.setUsername(username);

          Forum forum=forumRepository.getById(fid);
          
      	LocalDate dateOfPost=LocalDate.of(2021, 11,10 );
		//	LocalDate dateOfPost=LocalDate.now();
			question.setDateOfPost(dateOfPost);    
			
          List<Question> questionList=forum.getQuestion();
          
			questionList.add(question);
			forum.setQuestion(questionList);
			

			return questionRepository.save(question);
	}	
	
	
	/*
	 * 8. PUT api(s) for incrementing the Likes of the question & answer based on their IDs. 
	 */
	@PutMapping("/question/likes/{qid}")
	public Question updateQuestionAddLikes(@PathVariable("qid") Long qid) {
	
	   Question questionDB=questionRepository.getById(qid);

	  int like=questionDB.getLikes()+1;
	        questionDB.setLikes(like);
	
		return questionRepository.save(questionDB);
	}
	
	
	
	/*
	 * 9. PUT api for question and answer. Only those Users who have posted these
	 * questions and answers must be able to edit.
	 */
	@PutMapping("/question/{qid}")
	public Question updateQuestion(Principal principal, @PathVariable("qid") Long qid,
			@RequestBody Question questionNew) {
		String username = principal.getName();

		Question questionDB = questionRepository.getById(qid);

		if (questionNew.getQuestionText() != null)
			questionDB.setQuestionText(questionNew.getQuestionText());

		// questionDB.setDateOfPost(LocalDate.now());
		String userOwner = questionDB.getUsername();

		if (!username.equalsIgnoreCase(userOwner))
			throw new RuntimeException("user not allowed to edit the review");

		return questionRepository.save(questionDB);

	}

}
