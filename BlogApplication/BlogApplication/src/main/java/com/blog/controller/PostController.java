package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.blog.model.ApiResponse;
import com.blog.model.PostDTO;
import com.blog.services.PostService;

public class PostController {

	
	
	@Autowired
	private PostService pServices;
	
	@PostMapping("user/{userId}/category/{categoryId}/POST")
	public ResponseEntity<PostDTO> uploadPost(
			@RequestBody PostDTO post,
			@PathVariable int userId,
			@PathVariable int categoryId){
		PostDTO post1 = this.pServices.createPost(post, userId, categoryId);
		return new ResponseEntity<PostDTO>(post1,HttpStatus.CREATED);
	}
	
	@PutMapping("POST/{postId}")
	public ResponseEntity<PostDTO> updatePostInController(@RequestBody PostDTO psDto,@PathVariable("postId") int id){
		PostDTO updateDto = this.pServices.updatePost(psDto, id);
		return new ResponseEntity<PostDTO>(updateDto,HttpStatus.OK);
	}
	
	@GetMapping("POST/{postId}")
	public ResponseEntity<PostDTO> getSinglePostInController(@PathVariable("postId") int id){
		PostDTO pDto = this.pServices.getByIdPost(id);
		return new ResponseEntity<PostDTO>(pDto,HttpStatus.OK);
	}
	
	@GetMapping("POST")
	public ResponseEntity<List<PostDTO>> getAllPostInController(){
		List<PostDTO> psDtos = pServices.getAllPost();
		return new ResponseEntity<>(psDtos,HttpStatus.OK);
	}
	
	@DeleteMapping("POST/{postId}")
	public ApiResponse deletePostInControlller(@PathVariable("postId") int id) {
		this.pServices.delete(id);
		ApiResponse apr = new ApiResponse("this post deleted successfully",true);
		return apr;
	}
	
	@GetMapping("user/{userId}/POST")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable("userId") int id){
		List<PostDTO> allPostInUser = this.pServices.getPostByUser(id);
		return new ResponseEntity<List<PostDTO>>(allPostInUser,HttpStatus.OK);
	}
	
	@GetMapping("category/{categoryId}/POST")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable("categoryId") int id){
		List<PostDTO> allpostInCategory = this.pServices.getPostByCategory(id);
		return new ResponseEntity<List<PostDTO>>(allpostInCategory,HttpStatus.OK);
	}

}
