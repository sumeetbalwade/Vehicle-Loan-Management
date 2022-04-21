package com.capgemini.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.controller.AdminController;
import com.capgemini.controller.UserController;
import com.capgemini.exception.AdminNotFoundException;
import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.Account;
import com.capgemini.model.AdminDetails;
import com.capgemini.model.Approved;
import com.capgemini.model.EmiClass;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoginDto;
import com.capgemini.model.Status;
import com.capgemini.model.UserAdvanced;
import com.capgemini.model.UserBasic;
import com.capgemini.repository.AccountRepository;
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
	
	@Autowired
	AccountRepository accrepo;
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Override
	public boolean verifyAdminLogin(LoginDto login) throws AdminNotFoundException {
		if (!ubrepo.existsById(login.getEmail())) {
			logger.error("The User is not found.");
			throw new AdminNotFoundException("The User is not found.");
		}
		UserBasic ub = ubrepo.getById(login.getEmail());
		if (ub.getEmail().equals(login.getEmail()) && ub.getPassword().equals(login.getPassword()) && ub.getRole().equals("ROLE_ADMIN")) {
			logger.info("verifyAdminLogin true");
			return true;
		}
		logger.info("verifyAdminLogin false");
		return false;
	}

	@Override
	public ResponseEntity<String> adminRegisterService(UserBasic admin) throws UserAlreadyExistsException {
		if (ubrepo.existsById(admin.getEmail())) {
			logger.error("The User is not found.");
			throw new UserAlreadyExistsException("\"The User is not found.");
		}
		if(UserDao.isValidPassword(admin.getPassword())) {
			admin.setRole("ROLE_ADMIN");
			ubrepo.save(admin);
			logger.info("adminRegisterService successful");
			return new ResponseEntity<String>("Admin Registed Successfully",HttpStatus.OK);
		}else {
			logger.error("Passsword is not valid");
			throw new UserAlreadyExistsException("Passsword is not valid");
		}
		
	}

	@Override
	public ResponseEntity<String> modifyStatus(LoanAppTable loanapp) {
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
			logger.info("modifyStatus successful");
			//emidao.sendOTP(loanapp.getUser().get, s, s)
			return new ResponseEntity<String>("modifyStatus successful Accepted",HttpStatus.OK);
		}
		else {
			Status status=Status.REJECTED;
			String s=status.toString();
			loanapp.setStatus(s);
			larepo.save(loanapp);
			logger.info("modifyStatus successful");
			return new ResponseEntity<String>("modifyStatus successful REJECTED",HttpStatus.OK);
		}
		//LoanAppTable late=larepo.getById(loanapp.getChassisNo());
		//late.setStatus(loanapp.getStatus());
		//larepo.save(late);
		//larepo.modifyStatus(loanapp.getStatus(), loanapp.getChassisNo());
	}

	@Override
	public ResponseEntity<String> AddApprovedDetails(Approved approved) {
		logger.info("AddApprovedDetails successful");
		aprepo.save(approved);
		return new ResponseEntity<String>("AddApprovedDetails successful",HttpStatus.OK);
	}

	@Override
	public UserBasic getAdminRegistrationdetails(String email) throws AdminNotFoundException {
		
		if(ubrepo.getById(email).getRole().equals("ROLE_ADMIN")) {
			logger.info("getAdminRegistrationdetails successful");
			return ubrepo.getById(email);
		}
		logger.error("Admin Not Found");
		throw new AdminNotFoundException("Admin Not Found");
	}
	public List<UserBasic> findAllUserRegistrationDetails() {
		
		List<UserBasic> ul = new ArrayList<>();
		for (UserBasic ub : ubrepo.findAll()) {
			if(ub.getRole().equals("ROLE_USER")) {
				ul.add(ub);
			}
		}
		
		logger.info("findAllUserRegistrationDetails successful");
		return ul;
	}

	@Override
	public Account getAccountByEmailService(String email) {
		logger.info("getAccountByEmailService successful");
		return accrepo.getAccountByEmailService(email);
	}

	@Override
	public List<LoanAppTable> viewAllAcceptedLoanApplications() {
		logger.info("viewAllAcceptedLoanApplications successful");
		return larepo.viewAllAcceptedLoanApplications();
	}

	@Override
	public List<LoanAppTable> viewAllRejectedLoanApplications() {
		logger.info("viewAllRejectedLoanApplications successful");
		return larepo.viewAllRejectedLoanApplications();
	}

	@Override
	public List<UserBasic> viewAllApprovedUsers() {
		logger.info("viewAllApprovedUsers successful");
		return ubrepo.viewAllApprovedUsers();
	}

	@Override
	public List<UserBasic> viewAllRejectedUsers() {
		logger.info("viewAllRejectedUsers successful");
		return ubrepo.viewAllRejectedUsers();
	}

	@Override
	public List<UserBasic> viewAllPendingUsers() {
		logger.info("viewAllRejectedUsers successful");
		return ubrepo.viewAllPendingUsers();
	}
	
}
