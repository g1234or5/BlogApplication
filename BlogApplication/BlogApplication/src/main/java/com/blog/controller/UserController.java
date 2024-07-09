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
import com.blog.model.UserDTO;
import com.blog.services.UserServices;

public class UserController {

	
	@Autowired
	private UserServices userServices;
	
	
	@PostMapping("USER")
	public ResponseEntity<UserDTO> createUserController(@RequestBody UserDTO user) {
		UserDTO user1 = userServices.createUser(user);
		return new ResponseEntity<>(user1,HttpStatus.CREATED);
	}
	
	@PutMapping("USER/{userId}")
	public ResponseEntity<UserDTO> updateUserData(@RequestBody UserDTO user , @PathVariable("userId") int id){
		UserDTO user1 = userServices.updateUser(user, id);
		return new ResponseEntity<>(user1,HttpStatus.OK);
	}
	
	
	@GetMapping("USER/{USERId}")
	public ResponseEntity<UserDTO> getUserByIdInController(@PathVariable("USERId") int userId){
		return ResponseEntity.ok(userServices.getUserById(userId));
	}
	
	@GetMapping("USER")
	public ResponseEntity<List<UserDTO>> getAllUserInController(){
		return ResponseEntity.ok(userServices.getAllUsers());
	}
	
	@DeleteMapping("USER/{userId}")
	public ResponseEntity<ApiResponse> deleteUserByIdInController(@PathVariable("userId") int id){
		this.userServices.deleteUser(id);
		return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	
	}
	
}
