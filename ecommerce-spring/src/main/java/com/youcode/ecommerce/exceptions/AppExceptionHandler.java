package com.youcode.ecommerce.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> HandleUserException(Exception ex, WebRequest request ){
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> HandleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request ){
		
		Map<String, String> errors = new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> 
		errors.put(error.getField(), error.getDefaultMessage()));
		
		return new ResponseEntity<Object>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
}
