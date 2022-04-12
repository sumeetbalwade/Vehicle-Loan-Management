package com.capgemini;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public @ResponseBody ErrorMessage checkInvalidBookException(UserAlreadyExistsException e) {
		ErrorMessage err=new ErrorMessage(LocalDateTime.now(),e.getMessage());
		return err;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public @ResponseBody ErrorMessage checkBookCreationExceptionException(UserNotFoundException e) {
		ErrorMessage err=new ErrorMessage(LocalDateTime.now(),e.getMessage());
		return err;
	}
	
	
}