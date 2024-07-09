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
import com.blog.model.CategoryDTO;
import com.blog.services.CategoryService;

public class CategoryController {

	
	
	@Autowired
	private CategoryService cService;
	
	@PostMapping("Category")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO cDto) {
		CategoryDTO newCategory = this.cService.createCategory(cDto);
		return new  ResponseEntity<>(newCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("Category/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategoryInController(@RequestBody CategoryDTO cDto,@PathVariable("categoryId") int id){
		CategoryDTO updateDto = this.cService.updateCategory(cDto, id);
		return new ResponseEntity<>(updateDto,HttpStatus.OK);
	}
	
	@GetMapping("Category/{categoryId}")
	public ResponseEntity<CategoryDTO> getSingleCategoryInController(@PathVariable("categoryId") int id){
		CategoryDTO category = this.cService.getCategoryById(id);
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
	
	@GetMapping("Category")
	public ResponseEntity<List<CategoryDTO>> getAllCategoryInController(){
		List<CategoryDTO> allDto = this.cService.getAllCategories();
		return ResponseEntity.status(HttpStatus.OK).body(allDto);
	}
	
	
	@DeleteMapping("Category/{categoryId}")
	public ApiResponse deleteCategoryInController(@PathVariable("categoryId") int id) {
		this.cService.deleteCategory(id);
		ApiResponse ar = new ApiResponse("categories deleted successfully",true);
		return ar;
	}
}
