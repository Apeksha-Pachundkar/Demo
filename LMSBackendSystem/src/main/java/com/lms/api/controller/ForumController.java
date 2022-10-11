package com.lms.api.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.DtoQuestion;
import com.lms.api.dto.ForumDto;
import com.lms.api.dto.QuestionDto;
import com.lms.api.dto.QuestionDtoNew;
import com.lms.api.dto.Statdto;
import com.lms.api.model.Answer;
import com.lms.api.model.Forum;
import com.lms.api.model.Question;
import com.lms.api.model.User;
import com.lms.api.repository.AnswerRepository;
import com.lms.api.repository.ForumRepository;
import com.lms.api.repository.QuestionRepository;
import com.lms.api.repository.UserRepository;

@RestController
public class ForumController {

	@Autowired
	private ForumRepository forumRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	// add data in forum table
	@PostMapping("/forum")
	public Forum addForum(@RequestBody Forum forum) {
		return forumRepository.save(forum);
	}

	// add data in forum table
	@GetMapping("/forum")
	public List<Forum> getForum() {
		return forumRepository.findAll();
	}

	// add Question In Fourm
	@PostMapping("/forum/question/{fid}/{qid}")
	public Forum addQuestionInFourm(@PathVariable("fid") Long fid, @PathVariable("qid") Long qid) {
		//fetch forum details by forumId
		Forum forum = forumRepository.getById(fid);

		//fetch Question details by questionId
		Question question = questionRepository.getById(qid);
		
		//make List of questions and add given question to the List
		List<Question> questions = forum.getQuestion();
		questions.add(question);

		forum.setQuestion(questions);

		return forumRepository.save(forum);
	}

	/*
	 * Question: 3 Create an API to display all the Topic records(id&topics) in
	 * paged manner. Also add number of questions available in the DB for each
	 * topic.
	 */
	@GetMapping("/forum/topics")
	public List<ForumDto.ForumDtoFor3> getTopicsWithNoOfQuestions(
			@RequestParam(name = "page" ,required = false,defaultValue = "0") Integer page,
			@RequestParam(name = "size" ,required = false,defaultValue ="3")  Integer size
			) {
		Pageable pageable=PageRequest.of(page, size);
		
		//fetch all forum topics 
		List<Forum> forumList = forumRepository.findAll(pageable).getContent();
		
		List<ForumDto.ForumDtoFor3> dtoList = new ArrayList<>();	
		
		for(Forum f: forumList) {
	
			ForumDto.ForumDtoFor3 dto = new ForumDto.ForumDtoFor3();

			List<Question> questions=questionRepository.getByForumId(f.getId());
			
			dto.setTopicID(f.getId());
			dto.setTopic(f.getTopic());
			dto.setNumberOfQuestions(questions.size());
			
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	//  ************************* Alternate way ******************************
	@GetMapping("/forum/topics/alternate")
	public List<ForumDto.ForumDtoFor3> getTopicsWithNoOfQuestionsAlternate(
			@RequestParam(name = "page" ,required = false,defaultValue = "0") Integer page,
			@RequestParam(name = "size" ,required = false,defaultValue ="3")  Integer size
			) {
		Pageable pageable=PageRequest.of(page, size);
		
		//fetch all forum topics 
		List<Forum> forumList = forumRepository.findAll(pageable).getContent();
		
		//make dto list to store information of questions
		List<ForumDto.ForumDtoFor3> dtoList = new ArrayList<>();
		
		forumList.stream().forEach(f->{
			ForumDto.ForumDtoFor3 dto = new ForumDto.ForumDtoFor3();
			
			//fetch list of questions by forumId
			List<Question> questions=questionRepository.getByForumId(f.getId());
			
			dto.setTopicID(f.getId());
			dto.setTopic(f.getTopic());
			dto.setNumberOfQuestions(questions.size());
			
			dtoList.add(dto);
		});
		
		return dtoList;
	}
	
	
	       /* 
			10. Forum stats API
			
			{
			  totalNumberOfTopics: _____, 
			  totalNumberOfQuestions: ____, 
			  totalNumberOfAnswers: ______
			  totalNumberOfUsers: ______  [that have posted either Q or A]
			}
	 */
	@GetMapping("/forum/stats")
	public ForumDto.ForumStatsDto getForumStats() {
		
		//fetch all forum details 
		List<Forum> list=forumRepository.findAll();
		
		//fetch all Answer details
		List<Answer> answers=answerRepository.findAll();
		
		//fetch all question details
		List<Question> questions=questionRepository.findAll();
		
		//List<User> users=userRepository.findAll();
		
		//make object of ForumDto.ForumStatsDto
		ForumDto.ForumStatsDto forumStatsDto=new ForumDto.ForumStatsDto();
		
		forumStatsDto.setTotalNumberOfTopics(list.size());
		forumStatsDto.setTotalNumberOfQuestions(questions.size());
		forumStatsDto.setTotalNumberOfAnswers(answers.size());
		//forumStatsDto.setTotalNumberOfUsers(users.size());
	    
		//make list of all users who has posted answers
		List<String> userList1=answers.stream().map(u->u.getUsername()).collect(Collectors.toList());
		
		//make list of all users who has posted questions
		List<String> userList2=questions.stream().map(u->u.getUsername()).collect(Collectors.toList());
		
		userList1.addAll(userList2);
		
		long userCount=userList1.stream().distinct().count();
		
		forumStatsDto.setTotalNumberOfUsers((int)userCount);
		return forumStatsDto;
	}
	
	//********************************************************************************
	
	
	//1st question asked---list of answers based on forum id
	@GetMapping("/answersList/forum/{fid}")
	public List<Answer> listOfAnswersByFId(@PathVariable("fid") Long fid) {
		List<Answer> answersList=answerRepository.getAllAnswersByFId(fid);
		return answersList;
	}
	
	//2nd Question asked---list of questions based on forum id
	@GetMapping("/questionslist/forum/{fid}")
	public List<Question> listOfQuestionsByFId(@PathVariable("fid") Long fid) {
		List<Question> questions=questionRepository.getByForumId(fid);
		return questions;
	}
	
	//3rd question asked--------count no of likes for particular que/ans.
	@GetMapping("/question/count/likes/{qid}")
	public int getLikesForQuestion(@PathVariable("qid") Long qid) {
		Question question=questionRepository.getById(qid);
		int countLikes=question.getLikes();
		return countLikes;
	}
	
	//4th question asked---list of users who posted either a answer or question
	@GetMapping("/users/list/{fid}")
	public List<String> getListOfAllUsersByForumId(@PathVariable("fid") Long fid) {
		List<Question> qList=questionRepository.getAllQuestions(fid);
	    List<Answer> aList=answerRepository.getAllAnswersByFId(fid);
	
	    List<String> qList1=qList.stream().map(q->q.getUsername()).collect(Collectors.toList());
	    List<String> aList1=aList.stream().map(a->a.getUsername()).collect(Collectors.toList());
	    qList1.addAll(aList1);
	    
		return qList1.stream().distinct().collect(Collectors.toList());
		
	}
	
	//5th question asked-----count the questions asked by user
	@GetMapping("/count/questions/{uid}")
	public int getCountQuestionsByUId(@PathVariable("uid") long uid) {
		//String username=
				User user=userRepository.getById(uid);
				String username=user.getUsername();
		List<Question> questions=questionRepository.findAll();
		/*
		 * int count=0; 
		 * for(Question q: questions) {
		 * if(q.getUsername().equalsIgnoreCase(username)) 
		 * count++; }
		 */
		return (int)questions.stream().filter(q->q.getUsername().equalsIgnoreCase(username)).count();
		//return count;
	}
	
	//-6th question asked-sum of all likes of answer
	@GetMapping("/answer/sumOfLikes")
	public long sumAnswers() {
		List<Answer> answers=answerRepository.findAll();

		long sum=0;
		for(Answer a:answers) {
		      sum=sum+a.getLikes();
		}
		return sum;
	}
	
	
	//7th display all the questions based on desc order of post date
	@GetMapping("/allQuestions/dateOfPost")
	public List<Question> allQuestionByDate() {
		List<Question> questions=questionRepository.findAll();
		
		List<Question> sortedList=questions.stream()
                .sorted(Comparator.comparing(Question::getDateOfPost).reversed())
                .collect(Collectors.toList());
		return sortedList;
	}
	
	
	//display those questions who has at least one answer
	@GetMapping("/questionList/oneAnswer")
	public List<Question> questionListHasAtLeastOneAns() {
		List<Question> questions=questionRepository.findAll();
		List<Question> qlist=questions.stream().filter(q->q.getAnswer().size()!=0).collect(Collectors.toList());
		return qlist;
	}
	
	//list of questions posted after 2021-11-06
	@GetMapping("/questionList/dop")
	public List<Question> questionsDofPost() {
		List<Question> questions=questionRepository.findAll();
		List<Question> qlist=questions.stream().filter(q->q.getDateOfPost().isAfter(LocalDate.of(2021, 11, 06))).collect(Collectors.toList());
	     return qlist;
	}
	
	//display the names of questionText and username of questions that belong to given topic id
	@GetMapping("/questioninfo/{fid}")
	public List<QuestionDto.QuestionDatadto> questionTextAndUsername(@PathVariable("fid") Long fid) {
		List<Question> questions=questionRepository.getByForumId(fid);
		QuestionDto.QuestionDatadto dto=new QuestionDto.QuestionDatadto();
		List<QuestionDto.QuestionDatadto> dtolist=new ArrayList<>();
		for(Question q: questions) {
			dto.setQuestionText(q.getQuestionText());
			dto.setUsername(q.getUsername());
			dtolist.add(dto);
		}
		return dtolist;
	}
	
	//find the sum of all likes given to the answer based on questionid
	@GetMapping("/sumlikes/questionid/{qid}")
	public int getSum(@PathVariable("qid") Long qid) {
		//	Question question=questionRepository.getById(qid);
			List<Answer> answerList=answerRepository.getByQuestionId(qid);
			int sum=0;
		for(Answer a:answerList) {
			sum=sum+a.getLikes();
		}
		return sum;
	}
	
	//stat api 1)all questions likes 2)all answers likes 
	@GetMapping("/statapi/question-answer")
	public Statdto statApi() {
		List<Answer> answers=answerRepository.findAll();
		List<Question> questions=questionRepository.findAll();
		
		int answerSum=0;
	     for(Answer a:answers) {
	    	 answerSum=answerSum+a.getLikes();
	     }
	     
	 	int questionSum=0;
	     for(Question q:questions) {
	    	 questionSum=questionSum+q.getLikes();
	     }
	     
	     Statdto dto=new Statdto();
	     dto.setAnsLike(answerSum);
	     dto.setQueLike(questionSum);
	     return dto;
	}
	
	
	//display list of answers posted by given username for given topic id
	@GetMapping("/answerList/username-fid/{username}/{fid}")
	public List<Answer> answerList(@PathVariable("username") String username,@PathVariable("fid") Long fid) {
		List<Answer> answerlist=answerRepository.getAllAnswersByFId(fid);
		//User user=userRepository.findByUsername(username);
		List<Answer> answerList2=answerlist.stream().filter(a->a.getUsername().equalsIgnoreCase(username))
				.collect(Collectors.toList());
	      return answerList2;
	}
	
	
	//api to display all questions based on given topic id posted before given date
	@GetMapping("/questionList/before/{fid}")
	public List<Question> questionsBefore(@PathVariable("fid") Long fid,@RequestParam("date") LocalDate date) {
		List<Question> questions=questionRepository.getByForumId(fid);

		List<Question> questionlist=questions.stream()
				.filter(q->q.getDateOfPost().isBefore(LocalDate.of(date.getDayOfYear(), date.getMonth(), date.getDayOfMonth())))
				.collect(Collectors.toList());
	     return questionlist;
	    
	}
	
	
	//display list of answers based on qid sorted in desc order of dateofpost
	
	//write an api to display those questions only that has more than 2 answers and display only questiontext and dateofposted

	@GetMapping("/questionList/moreThanTwoAnswer")
	public List<DtoQuestion> questionListmoreThanTwoAnswer() {
		List<Question> questions=questionRepository.findAll();
		List<Question> qlist=questions.stream().filter(q->q.getAnswer().size()>2).collect(Collectors.toList());
		System.out.println(qlist);
		DtoQuestion dto=new DtoQuestion();
		List<DtoQuestion> dtoList=new ArrayList<>();
		
		for(Question q:qlist) {
			dto.setQuestionText(q.getQuestionText());
			dto.setDateOfPost(q.getDateOfPost());
			dtoList.add(dto);
		}
		return dtoList;
	}

	
	
   //Question-	Write an api to display list of all those questions that have one or less than one answer.
	//display questionText and username of user who has posted this question
	@GetMapping("/question/display")
	public List<QuestionDtoNew> displayQuestion() {
		List<Question> questions=questionRepository.findAll();
		
		List<Question> questionList=questions.stream().filter(q->q.getAnswer().size()<=1).collect(Collectors.toList());
		
		QuestionDtoNew dto=new QuestionDtoNew();
		
		List<QuestionDtoNew> dtoList=new ArrayList<>();
		for(Question q:questionList) {
			dto.setQuestionText(q.getQuestionText());
			dto.setUsername(q.getUsername());
			dtoList.add(dto);
			
		}
		return dtoList;
	}
	
	/*
	//display all user names who have posted at least  1 questions and 1 answers
	@GetMapping("/question/display")
	public List<String> displayUsernames() {
		List<Question> questions=questionRepository.findAll();
		List<Question> questionList=questions.stream().filter(q->q.getAnswer().size()<=1)
				.collect(Collectors.toList());
	
       List<Answer> answers=answerRepository.findAll();
		List<Answer> answerList=answers.stream().filter(a->a.getQuestion().size()<=1)
				.collect(Collectors.toList());
		
		List<String> questionUsers=questionList.stream().map(q->q.getUsername()).collect(Collectors.toList());
		List<String> answerUsers=answerList.stream().map(a->a.getUsername()).collect(Collectors.toList());
		
		questionUsers.addAll(answerUsers);
		List<String> users=questionUsers.stream().distinct().collect(Collectors.toList());
		
		return users;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
