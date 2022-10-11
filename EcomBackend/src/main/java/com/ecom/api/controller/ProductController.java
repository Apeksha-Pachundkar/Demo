package com.ecom.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.api.dto.ProductDto;
import com.ecom.api.model.Category;
import com.ecom.api.model.Product;
import com.ecom.api.model.Review;
import com.ecom.api.repository.CategoryRepository;
import com.ecom.api.repository.ProductRepository;
import com.ecom.api.repository.ReviewRepository;


@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ProductController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@PostMapping("/product/{cid}")
	public Product postProduct(@PathVariable("cid") Long cid, @RequestBody Product product) {
		Category category=categoryRepository.getById(cid);
		product.setCategory(category);
		
		return productRepository.save(product);
	}
	
	@GetMapping("/product/{cid}")
	public List<ProductDto> getAllProducts(@PathVariable("cid") Long cid) {
		List<ProductDto> listDto = new ArrayList<>();
		List<Product> list = productRepository.findByCategoryId(cid);
		
		for(Product p: list) {
			ProductDto dto = new ProductDto();
			dto.setId(p.getId());
			dto.setTitle(p.getTitle());
			dto.setShortDescription(p.getShortDescription());
			dto.setPrice(p.getPrice());
			dto.setCategoryId(p.getCategory().getId());
			dto.setCategoryName(p.getCategory().getName());
			dto.setReviewCount(reviewRepository.getReviewCount(p.getId()));
			listDto.add(dto);
		}
		
		return listDto;
	}
	
	@GetMapping("/review/product/{pid}")
	public List<Review> getAllReviewsByProductId(@PathVariable("pid") Long pid) {
		return reviewRepository.findByProductId(pid);
	}
	
	
	@PostMapping("/review/{pid}")
	public Review postReview(@PathVariable("pid") Long pid,@RequestBody Review review ) {
		Product product=productRepository.getById(pid);
		review.setProduct(product);
		return reviewRepository.save(review);
	}
	
	@DeleteMapping("/review/{rid}")
	public void deleteReview(@PathVariable("rid") Long rid) {
		reviewRepository.deleteById(rid);
	}
}
