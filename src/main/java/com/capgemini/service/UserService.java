package com.capgemini.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotAllowed;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoanUserHolder;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserAdvanced;
import com.capgemini.model.UserBasic;

public interface UserService {
	public ResponseEntity<String> UserRegisterService(UserBasic userbasic) throws UserAlreadyExistsException;
	public ResponseEntity<String> applyVehicleLoan(LoanUserHolder loanuserholder) throws UserNotFoundException;
	public ResponseEntity<String> resetPasswordService(UserBasic userbasic) throws UserNotFoundException;
	public ResponseEntity<String> modifyUserDetails(UserAdvanced user) throws UserNotFoundException;
	public UserBasic getUserRegistrationdetails(String email) throws UserNotFoundException;
	public UserAdvanced getUserDetailsService(String email) throws UserNotFoundException;
	public List<LoanAppTable> getAllLoanApplication(String email) throws UserNotFoundException;
	public List<Approved> viewAllApprovedByEmail(String email) throws UserNotFoundException;
	public LoanAppTable getLoanApplicationByChassis(int chassisNo) throws UserNotAllowed;
	public Approved viewApprovedByLoanId(int loanId) throws UserNotAllowed;
	public List<LoanAppTable> getAllRejectedByEmail(String email) throws UserNotAllowed;
	public boolean verifyUserLogin(LoginDto login) throws UserNotFoundException;
}
