package com.capgemini.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.model.Account;
import com.capgemini.model.AdminDetails;
import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.UserBasic;

@Service
public interface AdminDao {
	public void adminRegister(AdminDetails admin);
	public void modifyLoanApplicationStatus(LoanAppTable loanapp);
	public void ApprovedLoan(Approved approved);
	public AdminDetails showAdminDetailsByEmail(String email);
	public List<UserBasic> showAllUserRegistrationDetails();
	public Account getAccountByEmail(String email);
	public List<LoanAppTable> showAllAcceptedLoanApplications();
	public List<LoanAppTable> showAllRejectedLoanApplications();
	public List<UserBasic> showAllApprovedUsers();
	public List<UserBasic> showAllRejectedUsers();
	public List<UserBasic> showAllPendingUsers();
}
