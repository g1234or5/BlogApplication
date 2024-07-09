package com.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.model.CategoryDTO;
import com.blog.repository.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo cate;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        Category category = dtoToCategory(categoryDto);
        Category savedCategory = cate.save(category);
        return categoryToDto(savedCategory);
    }

    // Method to convert CategoryDTO to Category
    private Category dtoToCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    // Method to convert Category to CategoryDTO
    private CategoryDTO categoryToDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDto, int id) {
        Category category = cate.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = cate.save(category);
        return categoryToDto(updatedCategory);
    }

    public CategoryDTO getCategoryById(int id) {
        Category category = this.cate.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return this.categoryToDto(category);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> allCategories = this.cate.findAll();
        return allCategories.stream().map(this::categoryToDto).collect(Collectors.toList());
    }

    public void deleteCategory(int id) {
        Category category = this.cate.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        this.cate.delete(category);
    }
}
