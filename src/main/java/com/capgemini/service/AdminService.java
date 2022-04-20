package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Account;
import com.capgemini.model.AdminDetails;
import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserBasic;

public interface AdminService {
	public boolean verifyAdminLogin(LoginDto login);
	public void adminRegisterService(AdminDetails admin);
	public void modifyStatus(LoanAppTable loanapp);
	public void AddApprovedDetails(Approved approved);
	public AdminDetails getAdminRegistrationdetails(String email);
	public List<UserBasic> findAllUserRegistrationDetails(); 
	public Account getAccountByEmailService(String email);
	public List<LoanAppTable> viewAllAcceptedLoanApplications();
	public List<LoanAppTable> viewAllRejectedLoanApplications();
	public List<UserBasic> viewAllApprovedUsers();
	public List<UserBasic> viewAllRejectedUsers();
	public List<UserBasic> viewAllPendingUsers();
}
