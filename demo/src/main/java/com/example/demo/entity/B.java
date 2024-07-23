package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {

	A a;
	 	 
	B(A a) {
		this.a =a;
	}
  
/*
	public A getA() {
		return a;
	}
@Autowired
	public void setA(A a) {
		this.a = a;
	}
*/	
	
}
