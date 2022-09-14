package com.cts.usermanagement.exception;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorDetails> invalidTokenExceptionHandling(InvalidTokenException exception, WebRequest request){
		ErrorDetails errorDetails = 
				new ErrorDetails();
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorDetails> feignExceptionHandling(FeignException exception, WebRequest request){
		
		ErrorDetails error = new ErrorDetails(new Date(),String.valueOf(HttpStatus.NOT_FOUND.value()), "Token is either expired or invalid...");
		
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExceptionHandling(Exception exception, WebRequest request){
		ErrorDetails errorDetails = 
				new ErrorDetails();
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
