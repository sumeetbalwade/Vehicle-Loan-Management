package com.capgemini.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exception.AdminNotFoundException;
import com.capgemini.model.Account;
import com.capgemini.model.AdminDetails;
import com.capgemini.model.Approved;
import com.capgemini.model.EmiClass;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoginDto;
import com.capgemini.model.Status;
import com.capgemini.model.UserAdvanced;
import com.capgemini.model.UserBasic;
import com.capgemini.repository.AdminRepository;
import com.capgemini.repository.ApprovedRepository;
import com.capgemini.repository.EMIRepository;
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
	
	@Autowired
	EMIDao emidao;
	
	@Autowired
	EMIRepository emirepo;
	
	@Override
	public boolean verifyAdminLogin(LoginDto login) {
		if(!ubrepo.existsById(login.getEmail())) {
			//throw new UserNotFoundException("The User is not found.");
		}
		UserBasic ad=ubrepo.getById(login.getEmail());
		if(ad.getEmail().equals(login.getEmail()) && ad.getPassword().equals(login.getPassword()) && ad.getRole().equals("ROLE_ADMIN"))
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
		int ratio=(int)(loanapp.getAmount()/loanapp.getUser().getSalary());
		if(ratio<20) {
			Status status=Status.APPROVED;
			String s=status.toString();
			loanapp.setStatus(s);
			larepo.save(loanapp);
			double emi=emidao.EMICalculate(loanapp.getAmount(), loanapp.getTenure(),loanapp.getInterest());
			Approved ap=new Approved(emi,loanapp.getAppdate(),loanapp.getUser().getAccount(),loanapp);
			aprepo.save(ap);
			EmiClass emiclass=new EmiClass(LocalDate.now(),loanapp.getAmount()+emi,emi,loanapp.getAmount(),loanapp.getInterest(),loanapp.getAmount()+emi,s);
			emirepo.save(emiclass);
			//emidao.sendOTP(loanapp.getUser().get, s, s)
		}
		else {
			Status status=Status.REJECTED;
			String s=status.toString();
			loanapp.setStatus(s);
			larepo.save(loanapp);
		}
		//LoanAppTable late=larepo.getById(loanapp.getChassisNo());
		//late.setStatus(loanapp.getStatus());
		//larepo.save(late);
		//larepo.modifyStatus(loanapp.getStatus(), loanapp.getChassisNo());
	}

	@Override
	public void AddApprovedDetails(Approved approved) {
		aprepo.save(approved);
	}

	@Override
	public UserBasic getAdminRegistrationdetails(String email) throws AdminNotFoundException {
		
		if(ubrepo.getById(email).getRole().equals("ROLE_ADMIN")) {
			return ubrepo.getById(email);
		}
		throw new AdminNotFoundException("Admin Not Found");
	}
	public List<UserBasic> findAllUserRegistrationDetails() {
		
		List<UserBasic> ul = new ArrayList<>();
		for (UserBasic ub : ubrepo.findAll()) {
			if(ub.getRole().equals("ROLE_USER")) {
				ul.add(ub);
			}
		}
		return ul;
	}

	@Override
	public Account getAccountByEmailService(String email) {
		return adrepo.getAccountByEmailService(email);
	}

	@Override
	public List<LoanAppTable> viewAllAcceptedLoanApplications() {
		return larepo.viewAllAcceptedLoanApplications();
	}

	@Override
	public List<LoanAppTable> viewAllRejectedLoanApplications() {
		return larepo.viewAllRejectedLoanApplications();
	}

	@Override
	public List<UserBasic> viewAllApprovedUsers() {
		return ubrepo.viewAllApprovedUsers();
	}

	@Override
	public List<UserBasic> viewAllRejectedUsers() {
		return ubrepo.viewAllRejectedUsers();
	}

	@Override
	public List<UserBasic> viewAllPendingUsers() {
		return ubrepo.viewAllPendingUsers();
	}
	
}
