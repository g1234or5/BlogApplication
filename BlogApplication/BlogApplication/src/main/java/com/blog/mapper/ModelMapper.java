package com.blog.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapper {

	
	
	@Bean
	public ModelMapper modelMapper() {// conversion 
		return new ModelMapper();// ModelMapper obj = new ModelMapper();
	}
}
