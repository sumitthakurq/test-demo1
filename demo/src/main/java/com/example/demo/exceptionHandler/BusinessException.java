package com.example.demo.exceptionHandler;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
	HttpStatus status;
	String desc;
	
public	BusinessException() {
		super();
	}
public	BusinessException(HttpStatus status) {
		super();
		this.status = status;
	}
	
public	BusinessException(String desc) {
		super(desc);
		this.desc = desc;
	}
	
public	BusinessException(HttpStatus status,String desc) {
		super();
		this.status= status;
		this.desc = desc;
	}
 
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
