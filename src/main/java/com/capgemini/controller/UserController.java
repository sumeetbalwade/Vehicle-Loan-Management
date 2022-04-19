package com.capgemini.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dao.UserDao;
import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.helper.JwtUtil;
import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoanUserHolder;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserAdvanced;
import com.capgemini.model.UserBasic;

@RestController
public class UserController {
	
	@Autowired
	UserDao udao;
	

	@PostMapping(path="/registerUser")
	public void UserRegisterService(@RequestBody UserBasic userbasic) throws UserAlreadyExistsException {
		udao.UserRegisterService(userbasic);
	}
	
	@PutMapping(path="/applyVehicleLoan")
	public void applyVehicleLoan(@RequestBody LoanUserHolder loanuserholder) {
		udao.applyVehicleLoan(loanuserholder);
	}

	
	@PutMapping(path="/resetPasswordService")
	public void resetPasswordService(@RequestBody UserBasic userbasic) throws UserNotFoundException{
		udao.resetPasswordService(userbasic);
	}
	
	@PostMapping(path="/modifyUserDetails")
	public void modifyUserDetails(@RequestBody UserAdvanced user) throws UserNotFoundException {
		udao.modifyUserDetails(user);
	}
	
	@GetMapping(path="/getUserRegistrationdetails/{email}")
	public UserBasic getUserRegistrationdetails(@PathVariable String email) throws UserNotFoundException {
	
		return udao.getUserRegistrationdetails(email);
	}
	
	@GetMapping(path="/getUserDetailsService/{email}")
	public UserAdvanced getUserDetailsService(@PathVariable String email) throws UserNotFoundException {
		return udao.getUserDetailsService(email);
	}

	@GetMapping(path="/getAllLoanApplication/{email}")
	public List<LoanAppTable> getAllLoanApplication(@PathVariable String email) {
		return udao.getAllLoanApplication(email);
	}
	
	
	@GetMapping(path="/viewAllApprovedByEmail/{email}")
	public List<Approved> viewAllApprovedByEmail(@PathVariable String email) {
		return udao.viewAllApprovedByEmail(email);
	}
	
	@GetMapping(path="/getLoanApplicationByChassis/{chassisNo}")
	public LoanAppTable getLoanApplicationByChassis(@PathVariable int chassisNo) {
		return udao.getLoanApplicationByChassis(chassisNo);
	}

	@GetMapping(path="/viewApprovedByLoanId/{loanId}")
	public Approved viewApprovedByLoanId(@PathVariable int loanId) {
		return udao.viewApprovedByLoanId(loanId);
	}
	
	@GetMapping(path="/getAllRejectedByEmail/{email}")
	public List<LoanAppTable> getAllRejectedByEmail(@PathVariable String email) {
		return udao.getAllRejectedByEmail(email);
	}
	
	@PostMapping(path="/verifyUserLogin")
	public String verifyUserLogin(@RequestBody LoginDto login) throws UserNotFoundException {
		if(udao.verifyUserLogin(login)) {
			return "User Verified";
		}
		return "User not recognized";		
	}

}
