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
import com.capgemini.repository.UserBasicRepository;
import com.capgemini.service.UserService;

@Service
public class UserDao implements UserService{
	
	@Autowired
	UserBasicRepository ubrepo;

	@Override
	public void UserRegisterService(UserBasic userbasic) throws UserAlreadyExistsException {
		if(ubrepo.existsById(userbasic.getEmail())) {
			throw new UserAlreadyExistsException("The User already exists.");
		}
		ubrepo.save(userbasic);
	}

	
	@Override
	public void applyVehicleLoan(LoanAppTable loanapp, UserBasic userbasic, UserAdvanced userdetails) {
		// TODO Auto-generated method stub 
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoanAppTable> getAllLoanApplication(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Approved> viewAllApprovedByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoanAppTable getLoanApplicationByChassis(String chassisNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Approved viewApprovedByLoanId(int loanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoanAppTable> getAllRejectedByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
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
