package com.capgemini.service;

import java.util.List;

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
	public void UserRegisterService(UserBasic userbasic) throws UserAlreadyExistsException;
	public void applyVehicleLoan(LoanUserHolder loanuserholder) throws UserNotFoundException;
	public void resetPasswordService(UserBasic userbasic) throws UserNotFoundException;
	public void modifyUserDetails(UserAdvanced user) throws UserNotFoundException;
	public UserBasic getUserRegistrationdetails(String email) throws UserNotFoundException;
	public UserAdvanced getUserDetailsService(String email) throws UserNotFoundException;
	public List<LoanAppTable> getAllLoanApplication(String email) throws UserNotFoundException;
	public List<Approved> viewAllApprovedByEmail(String email) throws UserNotFoundException;
	public LoanAppTable getLoanApplicationByChassis(int chassisNo) throws UserNotAllowed;
	public Approved viewApprovedByLoanId(int loanId);
	public List<LoanAppTable> getAllRejectedByEmail(String email);
	public boolean verifyUserLogin(LoginDto login) throws UserNotFoundException;
}
