package com.blog.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.UserEntity;
import com.blog.exception.ResourceNotFoundException;

import com.blog.model.PostDTO;
import com.blog.repository.CategoryRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;

public class PostService {

	
	@Autowired
	private PostRepo pRepo;
	
	@Autowired
	private ModelMapper model;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	
	public PostDTO createPost(PostDTO postdto,int userId,int categoryId) {
		
		UserEntity  user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));

		Post post = DtoToPost(postdto);
		post.setImageName("default.png");
		post.setUploadDate(new Date());
		post.setCategory(category);
		post.setUser(user);		
		Post savedPost = pRepo.save(post);
		return this.PostToDto(savedPost);
	}

	
	public PostDTO updatePost(PostDTO postdto, int id) {
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		Post pr = pRepo.save(post);
		return this.PostToDto(pr);
	}

	
	public PostDTO getByIdPost(int id) {
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		return this.PostToDto(post);
	}

	
	public List<PostDTO> getAllPost() {
		List<Post> allPosts = pRepo.findAll();
		List<PostDTO> allDtosPosts = allPosts.stream().map(psa-> (PostToDto(psa))).collect(Collectors.toList());
		return allDtosPosts;
	}

	
	public void delete(int id) {
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		this.pRepo.delete(post);
		
	}

	
	public List<PostDTO> getPostByCategory(int id) {
		
		Category ct = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		List<Post> catePosts = pRepo.findByCategory(ct);
		List<PostDTO> pstDtos = catePosts.stream().map(ps-> (PostToDto(ps))).collect(Collectors.toList());
		return pstDtos;
	}

	
	public List<PostDTO> getPostByUser(int id) {
		
		UserEntity us = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		List<Post> userpst = pRepo.findByUser(us);
		List<PostDTO> userDtos = userpst.stream().map(usp-> (PostToDto(usp))).collect(Collectors.toList());
		return userDtos;
	}
	
	public Post DtoToPost(PostDTO pd) {
		Post prPost = this.model.map(pd,Post.class);
		return prPost;
	}
	
	public PostDTO PostToDto(Post post) {
		PostDTO pDto = model.map(post, PostDTO.class);
		return pDto;
	}

}
