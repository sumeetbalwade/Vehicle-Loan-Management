package com.capgemini.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Account;
import com.capgemini.model.AdminDetails;
import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserBasic;
import com.capgemini.repository.AdminRepository;
import com.capgemini.repository.ApprovedRepository;
import com.capgemini.repository.LoanApplicationRepository;
import com.capgemini.repository.UserBasicRepository;
import com.capgemini.service.AdminService;

@Service
public class AdminDao implements AdminService{

	@Autowired
	AdminRepository adrepo;
	
	@Autowired
	UserBasicRepository ubrepo;
	
	@Autowired
	ApprovedRepository aprepo;
	
	@Autowired
	LoanApplicationRepository larepo;
	
	@Override
	public boolean verifyAdminLogin(LoginDto login) {
		if(!adrepo.existsById(login.getEmail())) {
			//throw new UserNotFoundException("The User is not found.");
		}
		AdminDetails ad=adrepo.getById(login.getEmail());
		if(ad.getEmail().equals(login.getEmail()) && ad.getPassword().equals(login.getPassword()))
			return true;
		return false;
	}

	@Override
	public void adminRegisterService(AdminDetails admin) {
		if(adrepo.existsById(admin.getEmail())) {
			//throw new UserAlreadyExistsException("The User already exists.");
		}
		adrepo.save(admin);
		
	}

	@Override
	public void modifyStatus(LoanAppTable loanapp) {
		adrepo.modifyStatus(loanapp.getStatus(), loanapp.getChassisNo());
	}

	@Override
	public void AddApprovedDetails(Approved approved) {
		aprepo.save(approved);
	}

	@Override
	public AdminDetails getAdminRegistrationdetails(String email) {
		return adrepo.getById(email);
	}

	@Override
	public List<UserBasic> findAllUserRegistrationDetails() {
		return ubrepo.findAll();
	}

	@Override
	public Account getAccountByEmailService(String email) {
		return adrepo.getAccountByEmailService(email);
	}

	@Override
	public List<LoanAppTable> viewAllAcceptedLoanApplications() {
		return adrepo.viewAllAcceptedLoanApplications();
	}

	@Override
	public List<LoanAppTable> viewAllRejectedLoanApplications() {
		return adrepo.viewAllRejectedLoanApplications();
	}

	@Override
	public List<UserBasic> viewAllApprovedUsers() {
		return adrepo.viewAllApprovedUsers();
	}

	@Override
	public List<UserBasic> viewAllRejectedUsers() {
		return adrepo.viewAllRejectedUsers();
	}

	@Override
	public List<UserBasic> viewAllPendingUsers() {
		return adrepo.viewAllPendingUsers();
	}
	
}
