package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dao.AdminDao;
import com.capgemini.model.Account;
import com.capgemini.model.AdminDetails;
import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserBasic;

@RestController
public class AdminController {

	@Autowired
	AdminDao adao;
	
	@PostMapping(path="/verifyAdminLogin")
	public boolean verifyAdminLogin(@RequestBody LoginDto login) {
		return adao.verifyAdminLogin(login);
	}
	
	@PutMapping(path="/modifyStatus")
	public void modifyStatus(@RequestBody LoanAppTable loanapp) {
		adao.modifyStatus(loanapp);
	}
	
	@PostMapping(path="/AddApprovedDetails")
	public void AddApprovedDetails(@RequestBody Approved approved) {
		adao.AddApprovedDetails(approved);
	}
	
	@GetMapping(path="/getAdminRegistrationdetails/{email}")
	public AdminDetails getAdminRegistrationdetails(@PathVariable String email) {
		return adao.getAdminRegistrationdetails(email);
	}
	
	@GetMapping(path="/findAllUserRegistrationDetails")
	public List<UserBasic> findAllUserRegistrationDetails() {
		return adao.findAllUserRegistrationDetails();
	}
	
	@GetMapping(path="/getAccountByEmailService/{email}")
	public Account getAccountByEmailService(@PathVariable String email) {
		return adao.getAccountByEmailService(email);
	}
	
	@GetMapping(path="/viewAllAcceptedLoanApplications")
	public List<LoanAppTable> viewAllAcceptedLoanApplications() {
		return adao.viewAllAcceptedLoanApplications();
	}

	@GetMapping(path="/viewAllRejectedLoanApplications")
	public List<LoanAppTable> viewAllRejectedLoanApplications() {
		return adao.viewAllRejectedLoanApplications();
	}
	
	
	@GetMapping(path="/viewAllApprovedUsers")
	public List<UserBasic> viewAllApprovedUsers() {
		return adao.viewAllApprovedUsers();
	}
	
	@GetMapping(path="/viewAllRejectedUsers")
	public List<UserBasic> viewAllRejectedUsers() {
		return adao.viewAllRejectedUsers();
	}
	
	@GetMapping(path="/viewAllPendingUsers")
	public List<UserBasic> viewAllPendingUsers() {
		return adao.viewAllPendingUsers();
	}
	
	
}
