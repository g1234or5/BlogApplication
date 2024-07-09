package com.blog.exception;

public class ResourceNotFoundException  extends RuntimeException {

	
	
	
		
		String resource;
		String fieldName;
		int fieldValue;
		
		public ResourceNotFoundException(String resource,String fieldName,int fieldValue) {
			super(String.format("%s not found  with  %s : %d",resource,fieldName,fieldValue));
			this.resource = resource;
			this.fieldName = fieldName;
			this.fieldValue = fieldValue;
		}

		public ResourceNotFoundException(String resource, String fieldName, Integer userId) {
			
		}
}
