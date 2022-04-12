package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dao.UserDao;
import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserBasic;

@RestController
public class UserController {
	
	@Autowired
	UserDao udao;
	
	@PostMapping(path="/registerUser")
	public void UserRegisterService(@RequestBody UserBasic userbasic) throws UserAlreadyExistsException {
		udao.UserRegisterService(userbasic);
	}
	
	@GetMapping(path="/getUserRegistrationdetails/{email}")
	public UserBasic getUserRegistrationdetails(@PathVariable String email) throws UserNotFoundException {
		return udao.getUserRegistrationdetails(email);
	}
	
	@PutMapping(path="/resetPasswordService")
	public void resetPasswordService(@RequestBody UserBasic userbasic) throws UserNotFoundException{
		udao.resetPasswordService(userbasic);
	}
	
	@PostMapping(path="/verifyUserLogin")
	public String verifyUserLogin(@RequestBody LoginDto login) throws UserNotFoundException {
		if(udao.verifyUserLogin(login)) {
			return "User Verified";
		}
		return "User not recognized";		
	}

}
