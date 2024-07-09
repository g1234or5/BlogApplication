package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.blog.model.ApiResponse;
import com.blog.model.CommentDTO;
import com.blog.services.CommentService;

public class CommentController {

	
	
	@Autowired
	private CommentService cServices;
	
	@PostMapping("POST/{id}/comments")
	public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO cDto,@PathVariable("id") int id) {
		CommentDTO ans = this.cServices.addCommentDto(cDto, id);
		return  new ResponseEntity<>(ans,HttpStatus.CREATED);
	}
	
	@DeleteMapping("POST/{id}/comments")
	public ApiResponse deleteCommentInController(@PathVariable("id") int id) {
		this.cServices.deleteComment(id);
		ApiResponse ar = new ApiResponse("comments delete successfully",true);
		return ar;
	}

}
