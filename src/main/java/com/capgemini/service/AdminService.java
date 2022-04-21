package com.capgemini.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capgemini.exception.AdminNotFoundException;
import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.model.Account;
import com.capgemini.model.AdminDetails;
import com.capgemini.model.Approved;
import com.capgemini.model.EmiClass;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserBasic;

public interface AdminService {
	public boolean verifyAdminLogin(LoginDto login) throws AdminNotFoundException;
	public ResponseEntity<String> adminRegisterService(UserBasic admin) throws UserAlreadyExistsException;
	public ResponseEntity<String> modifyStatus(LoanAppTable loanapp);
	public ResponseEntity<String> AddApprovedDetails(Approved approved);
	public UserBasic getAdminRegistrationdetails(String email) throws AdminNotFoundException;
	public List<UserBasic> findAllUserRegistrationDetails(); 
	public Account getAccountByEmailService(String email);
	public List<LoanAppTable> viewAllAcceptedLoanApplications();
	public List<LoanAppTable> viewAllRejectedLoanApplications();
	public List<UserBasic> viewAllApprovedUsers();
	public List<UserBasic> viewAllRejectedUsers();
	public List<UserBasic> viewAllPendingUsers();
}
