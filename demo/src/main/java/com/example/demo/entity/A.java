package com.example.demo.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class A 
{
	

  B b;

/*
A( B b) {
	this.b =b;
}
*/

A(@Lazy B b) {
	this.b =b;
}

/*
public B getB() {
	return b;
}
@Autowired
public void setB(B b) {
	this.b = b;
}
*/

}
