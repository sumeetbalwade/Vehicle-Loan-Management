package com.capgemini.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserAdvanced;
import com.capgemini.model.UserBasic;
import com.capgemini.repository.UserAdvancedRepository;
import com.capgemini.repository.UserBasicRepository;
import com.capgemini.service.UserService;

@Service
public class UserDao implements UserService{
	
	@Autowired
	UserBasicRepository ubrepo;

	@Autowired
	UserAdvancedRepository uadrepo;
	
	@Override
	public void UserRegisterService(UserBasic userbasic) throws UserAlreadyExistsException {
		if(ubrepo.existsById(userbasic.getEmail())) {
			throw new UserAlreadyExistsException("The User already exists.");
		}
		ubrepo.save(userbasic);
	}

	
	@Override
	public void applyVehicleLoan(LoanAppTable loanapp, UserBasic userbasic) {
		
	}

	@Override
	public void resetPasswordService(UserBasic userbasic) throws UserNotFoundException{
		if(!ubrepo.existsById(userbasic.getEmail())) {
			throw new UserNotFoundException("The User is not found.");
		}
		UserBasic ub=ubrepo.getById(userbasic.getEmail());
		ub.setPassword(userbasic.getPassword());
		ubrepo.save(ub);
	}

	@Override
	public void modifyUserDetails(UserAdvanced user) {
		if(uadrepo.existsById(user.getUserId())) {
			
		}
	}

	@Override
	public UserBasic getUserRegistrationdetails(String email) throws UserNotFoundException {
		if(!ubrepo.existsById(email)){
			throw new UserNotFoundException("The User is not found.");
		}
		return ubrepo.getById(email);
	}

	@Override
	public UserAdvanced getUserDetailsService(String email) {
		return ubrepo.getUserDetailsService(email);
	}

	@Override
	public List<LoanAppTable> getAllLoanApplication(String email) {
		return ubrepo.getAllLoanApplication(email);
	}

	@Override
	public List<Approved> viewAllApprovedByEmail(String email) {
		return ubrepo.viewAllApprovedByEmail(email);
	}

	@Override
	public LoanAppTable getLoanApplicationByChassis(String chassisNo) {
		return ubrepo.getLoanApplicationByChassis(chassisNo);
	}

	@Override
	public Approved viewApprovedByLoanId(int loanId) {
		return ubrepo.viewApprovedByLoanId(loanId);
	}

	@Override
	public List<LoanAppTable> getAllRejectedByEmail(String email) {
		return ubrepo.getAllRejectedByEmail(email);
	}

	@Override
	public boolean verifyUserLogin(LoginDto login) throws UserNotFoundException {
		if(!ubrepo.existsById(login.getEmail())) {
			throw new UserNotFoundException("The User is not found.");
		}
		UserBasic ub=ubrepo.getById(login.getEmail());
		if(ub.getEmail().equals(login.getEmail()) && ub.getPassword().equals(login.getPassword()))
			return true;
		return false;
	}
}
