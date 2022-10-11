package com.lms.api.controller;

import java.security.Principal;
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

import com.lms.api.dto.ReviewDto;
import com.lms.api.model.Course;
import com.lms.api.model.Review;
import com.lms.api.model.User;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.ReviewRepository;
import com.lms.api.repository.UserRepository;


@RestController
public class ReviewController {
 
	@Autowired
	private ReviewRepository reviewRepository;
	

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * POST API for review
	 */

	@PostMapping("/review/{cid}")
	public Review postReview(@RequestBody Review review,@PathVariable("cid") Long cid,Principal principal) {
		Course course=courseRepository.getById(cid);
		User user=userRepository.findByUsername(principal.getName());
		review.setCourse(course);
		review.setUser(user);
		
		return reviewRepository.save(review);
	}
	
	/*
	 * Get All by Course ID
	 */
	
	@GetMapping("/review/{cid}")
	public List<Review> getByCourseId(@PathVariable("cid") Long cid) {
		/*
		 * Step1: fetch all reviews based on courseId
		 */
		List<Review> list=reviewRepository.getByCourseId(cid);
		return list;
	}
	
	/*
	 * Get all Reviews
	 * {
	 *   id:
	 *   content:
	 *   rating:
	 *   userId:
	 *   username:
	 *   name:
	 *   email:
	 *   courseId:
	 *   courseName:
	 *   learningTrackId:
	 *   learningTrack:
	 *  
	 * }
	 */
	/*
	 * @GetMapping("/reviews")
	 *  public List<Review> getAllReviews() { 
	 *  List<Review> list=reviewRepository.findAll();
	 *   return list; 
	 *   }
	 */
	
	@GetMapping("/reviews")
	public List<ReviewDto.GetAllReviewsDto> getAllReviews() {
		List<Review> list=reviewRepository.findAll();
		List<ReviewDto.GetAllReviewsDto> dtoList=new ArrayList<>();
	
		for (Review r : list) {
			ReviewDto.GetAllReviewsDto dto=new ReviewDto.GetAllReviewsDto();
			dto.setId(r.getId());
			dto.setContent(r.getContent());
			dto.setRating(r.getRating());
			dto.setUserId(r.getUser().getId());
			dto.setUsername(r.getUser().getUsername());
			dto.setName(r.getUser().getName());
			dto.setEmail(r.getUser().getEmail());
			dto.setCourseId(r.getCourse().getId());
			dto.setCourseName(r.getCourse().getName());
			dto.setLearningTrackId(r.getCourse().getLearningTrack().getId());
			dto.setLearningTrack(r.getCourse().getLearningTrack().getName());
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	/*
	 * Get Review by course ID sort by rating(1-5) DESC 
	 */
	@GetMapping("/review/sortedbyRatingsDESC/{cid}")
	public List<Review> getReviewByCourseIdSortByRatingDESC(@PathVariable("cid") Long cid) {
		List<Review> list=reviewRepository.getByCourseId(cid);
		List<Review> sortedList=list.stream()
				                    .sorted(Comparator.comparingDouble(Review::getRating).reversed())
				                    .collect(Collectors.toList());
		return sortedList;
		
	}
	
	/*
	 * update Review: User that has created the review can update it
	 */
	@PutMapping("/review/{rid}")
	public Review updateReview(Principal principal,@PathVariable("rid") Long rid,@RequestBody Review reviewNew) {
		String username=principal.getName();
		Review reviewDB=reviewRepository.getById(rid);
		
		if(reviewNew.getContent()!=null)
			reviewDB.setContent(reviewNew.getContent());
		
		 if(reviewNew.getRating()!=0)
			reviewDB.setRating(reviewNew.getRating());
		 
		 String userOwner=reviewDB.getUser().getUsername();
		
		if(! username.equalsIgnoreCase(userOwner))
			throw new RuntimeException("user not allowed to edit the review");
		
			return reviewRepository.save(reviewDB);
	}
	
	
	/*
	 * statistical API
	 * get reviews by course id in following format
	 * {
	 *      totalReviews : 23,
	 *      5 star:19,
	 *      4 star:2,
	 *      3 star:1,
	 *      2 star:0,
	 *      1 star 1
	 * }
	 */
	@GetMapping("/review/stats/{cid}")
	public ReviewDto getReviewStatsByCourseId(@PathVariable("cid") Long cid) {
		List<Review> list=reviewRepository.getByCourseId(cid);
		
		 ReviewDto reviewDto=new ReviewDto();
		reviewDto.setTotalReviews(list.size());
		reviewDto.setFiveStar(list.stream().filter(r->r.getRating()==5).collect(Collectors.toList()).size());
		reviewDto.setFourStar(list.stream().filter(r->r.getRating()==4).collect(Collectors.toList()).size());
		reviewDto.setThreeStar(list.stream().filter(r->r.getRating()==3).collect(Collectors.toList()).size());
		reviewDto.setTwoStar(list.stream().filter(r->r.getRating()==2).collect(Collectors.toList()).size());
		reviewDto.setOneStar(list.stream().filter(r->r.getRating()==1).collect(Collectors.toList()).size());
		return reviewDto;
	}
}
