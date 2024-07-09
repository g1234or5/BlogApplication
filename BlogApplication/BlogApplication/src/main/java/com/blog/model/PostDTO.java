package com.blog.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	
	private String title;
	private String content;
	private String imageName;
	private Date uploadDate;
	private CategoryDTO category;
	private UserDTO user;
	private Set<CommentDTO> comments = new HashSet<>();

}
