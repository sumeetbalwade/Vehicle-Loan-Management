package com.capgemini.dao;

import java.util.List;

import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.UserAdvanced;
import com.capgemini.model.UserBasic;

public interface UserDao {
	public void userRegister(UserBasic userbasic);
	public void ApplyLoan(LoanAppTable loanapp);
	public void passwordReset(UserBasic userbasic);
	public void editUserDetails(UserAdvanced user);
	public UserBasic showUserRegistrationInformation(String email);
	public UserAdvanced showUserDetailsInformation(String email);
	public List<LoanAppTable> showAllLoanApplication(String email);
	public LoanAppTable showLoanApplicationByChassis(String chassisNo);
	public List<Approved> showAllApprovedByEmail(String email);
	public List<LoanAppTable> showAllRejectedByEmail(String email);
	public Approved showApprovedByLoanId(int loanId);
}
