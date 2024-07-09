package com.blog.model;


import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {
	
	private int id;
	@Column(name="title")
	private String categoryTitle;
	@Column(name="Description")
	private String categoryDescription;

}
