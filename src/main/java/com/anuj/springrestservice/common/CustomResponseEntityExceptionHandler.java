package com.anuj.springrestservice.common;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.anuj.springrestservice.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
		final ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), 
						HttpStatus.NOT_FOUND.value(), 
						ex.getMessage(), 
						request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse , HttpStatus.NOT_FOUND);
	}
	
}
