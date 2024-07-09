package com.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;

import com.blog.model.CommentDTO;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepo;



@Service
public class CommentService {

	
        @Autowired
		private  CommentRepo cRepo;
		
		@Autowired
		private PostRepo psPost;
		
		private ModelMapper modelMapper;
		
		public CommentDTO addCommentDto(CommentDTO cDTO, int postId) {
	        Post post = this.psPost.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
	        Comment comment = modelMapper.map(cDTO, Comment.class);
	        comment.setPost(post);
	        Comment saveComment = cRepo.save(comment);
	        return modelMapper.map(saveComment, CommentDTO.class);
	    }

		public void deleteComment(int id) {
	        Comment comment = this.cRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comment","id",id));
	        this.cRepo.delete(comment);
			
		}

}
