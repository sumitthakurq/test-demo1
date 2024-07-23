package com.example.demo.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandling  extends ResponseEntityExceptionHandler
{
	 private static final org.slf4j.Logger log = 
			    org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandling.class);

/*	@ExceptionHandler(BusinessException.class)
public	ResponseEntity<String> handleEmptyInput(BusinessException businessException) {
		log.info("Inside the handleEmptyInput  file ");
	//return  new ResponseEntity<String>(businessException.getDesc(),businessException.status);
		return  new ResponseEntity<String>(businessException.getMessage(),businessException.status);	
	}*/
	
		@ExceptionHandler(BusinessException.class)
		public	ResponseEntity<ApiError> handleEmptyInput(BusinessException businessException) {
				List<String> details = new ArrayList<>();
				details.add(businessException.getMessage());
				ApiError err = new ApiError(
		                LocalDateTime.now(),
		                HttpStatus.BAD_REQUEST, 
		                "ZZZZBad Request",
		                details);
				log.info("Sumit error is: "+businessException.getMessage());
				return  new ResponseEntity<ApiError>(err, err.getStatus());	
			}
	 /*
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return  new ResponseEntity<Object>("Please change method Type",HttpStatus.METHOD_NOT_ALLOWED);
	}
	*/
	 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	  
}
