package com.lms.api.dto;

import java.time.LocalDate;
import java.util.List;



public class ForumDto {

	private Long topicID;
	private String topic;
	//private List<Question> questions;
	private List<ForumDto.QuestionDto4> questions;


	public static class QuestionDto4{
		private long questionsId;
		private String questionsText;
		private int questionsLikes;
		private String questionsUsername;
		
		private LocalDate questionPostDate;
		
		public LocalDate getQuestionPostDate() {
			return questionPostDate;
		}
		public void setQuestionPostDate(LocalDate questionPostDate) {
			this.questionPostDate = questionPostDate;
		}
		private int numberAnswers;
		public long getQuestionsId() {
			return questionsId;
		}
		public void setQuestionsId(long questionsId) {
			this.questionsId = questionsId;
		}
		public String getQuestionsText() {
			return questionsText;
		}
		public void setQuestionsText(String questionsText) {
			this.questionsText = questionsText;
		}
		public int getQuestionsLikes() {
			return questionsLikes;
		}
		public void setQuestionsLikes(int questionsLikes) {
			this.questionsLikes = questionsLikes;
		}
		public String getQuestionsUsername() {
			return questionsUsername;
		}
		public void setQuestionsUsername(String questionsUsername) {
			this.questionsUsername = questionsUsername;
		}
		public int getNumberAnswers() {
			return numberAnswers;
		}
		public void setNumberAnswers(int numberAnswers) {
			this.numberAnswers = numberAnswers;
		}
		
		
	}
	
	
	public static class ForumStatsDto {

			private int totalNumberOfTopics;
			private int totalNumberOfQuestions;
			private int totalNumberOfAnswers;
			private int totalNumberOfUsers;
	
			public int getTotalNumberOfTopics() {
				return totalNumberOfTopics;
			}
	
			public void setTotalNumberOfTopics(int totalNumberOfTopics) {
				this.totalNumberOfTopics = totalNumberOfTopics;
			}
	
			public int getTotalNumberOfQuestions() {
				return totalNumberOfQuestions;
			}
	
			public void setTotalNumberOfQuestions(int totalNumberOfQuestions) {
				this.totalNumberOfQuestions = totalNumberOfQuestions;
			}
	
			public int getTotalNumberOfAnswers() {
				return totalNumberOfAnswers;
			}
	
			public void setTotalNumberOfAnswers(int totalNumberOfAnswers) {
				this.totalNumberOfAnswers = totalNumberOfAnswers;
			}
	
			public int getTotalNumberOfUsers() {
				return totalNumberOfUsers;
			}
	
			public void setTotalNumberOfUsers(int totalNumberOfUsers) {
				this.totalNumberOfUsers = totalNumberOfUsers;
			}

	}

	public static class ForumDtoFor3 {
		private Long topicID;
		private String topic;
		private int numberOfQuestions;
		public Long getTopicID() {
			return topicID;
		}
		public void setTopicID(Long topicID) {
			this.topicID = topicID;
		}
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public int getNumberOfQuestions() {
			return numberOfQuestions;
		}
		public void setNumberOfQuestions(int numberOfQuestions) {
			this.numberOfQuestions = numberOfQuestions;
		}
		
		
		
    }
	public Long getTopicID() {
		return topicID;
	}

	public void setTopicID(Long topicID) {
		this.topicID = topicID;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
/*
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
*/

	public List<ForumDto.QuestionDto4> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ForumDto.QuestionDto4> questions) {
		this.questions = questions;
	}
	

}
