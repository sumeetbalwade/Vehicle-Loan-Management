package com.capgemini.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.controller.UserController;
import com.capgemini.exception.UserAlreadyExistsException;
import com.capgemini.exception.UserNotAllowed;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.helper.JwtUtil;
import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoanUserHolder;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserAdvanced;
import com.capgemini.model.UserBasic;
import com.capgemini.repository.ApprovedRepository;
import com.capgemini.repository.LoanApplicationRepository;
import com.capgemini.repository.UserAdvancedRepository;
import com.capgemini.repository.UserBasicRepository;
import com.capgemini.service.UserService;

@Service
public class UserDao implements UserService {

	@Autowired
	UserBasicRepository ubrepo;

	@Autowired
	UserAdvancedRepository uadrepo;
	
	@Autowired
	LoanApplicationRepository larepo;
	
	@Autowired
	ApprovedRepository aprepo;
	
	Logger logger = LoggerFactory.getLogger(UserController.class); 
	public static boolean isValidPassword(String password) {

		// Regex to check valid password.
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the password is empty
		// return false
		if (password == null) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given password
		// and regular expression.
		Matcher m = p.matcher(password);

		// Return if the password
		// matched the ReGex
		return m.matches();
	}

	@Override
	public ResponseEntity<String> UserRegisterService(UserBasic userbasic) throws UserAlreadyExistsException {
		if (ubrepo.existsById(userbasic.getEmail())) {
			logger.error("The User already exists.");
			throw new UserAlreadyExistsException("The User already exists.");
		}
		if(isValidPassword(userbasic.getPassword())) {
			userbasic.setRole("ROLE_USER");
			ubrepo.save(userbasic);
			logger.info("User has been added");
			return new ResponseEntity<String>("User Registed Successfully",HttpStatus.OK);
		}else {
			logger.error("Passsword is not valid");
			throw new UserAlreadyExistsException("Passsword is not valid");
		}

	}

	@Override
	public ResponseEntity<String> applyVehicleLoan(LoanUserHolder loanuserholder) throws UserNotFoundException {
		
		
		
		if (ubrepo.existsById(loanuserholder.email)) {
			UserBasic ub=ubrepo.getById(loanuserholder.email);
			if(!ub.getUsername().equals(JwtUtil.getTokenUsername())) {
				logger.error("The User is NOT ALLOWED.");
				throw new UserNotFoundException("The User is NOT ALLOWED.");
			}
			loanuserholder.lat.setAppdate(LocalDate.now());
			loanuserholder.lat.setUser(ub.getUserdetails());
			larepo.save(loanuserholder.lat);
			logger.info("Loan Applied Successfully");
			return new ResponseEntity<String>("applyed VehicleLoan Successfully",HttpStatus.OK);
			
		}
		else {
			logger.error("User doesn't exist");
			throw new UserNotFoundException("User doesn't exist");
		}
	}

	@Override
	public ResponseEntity<String> resetPasswordService(UserBasic userbasic) throws UserNotFoundException {
		if (!ubrepo.existsById(userbasic.getEmail())) {
			logger.error("The User is not found.");
			throw new UserNotFoundException("The User is not found.");
			
		}
		
		if(!userbasic.getUsername().equals(JwtUtil.getTokenUsername())) {
			logger.error("The User is NOT ALLOWED."); 
			throw new UserNotFoundException("The User is NOT ALLOWED.");
		}
		UserBasic ub = ubrepo.getById(userbasic.getEmail());
		ub.setPassword(userbasic.getPassword());
		ubrepo.save(ub);
		logger.info("reseted Password");
		return new ResponseEntity<String>("Reseted Password Successfully",HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<String> modifyUserDetails(UserAdvanced user) throws UserNotFoundException {
		if (uadrepo.existsById(user.getUserId())) {
			
			if(!ubrepo.getUserByUserId(user.getUserId()).getUsername().equals(JwtUtil.getTokenUsername())) {
				logger.error("The User is NOT ALLOWED."); 
				throw new UserNotFoundException("The User is NOT ALLOWED.");
			}
			uadrepo.save(user);
			logger.info("reseted Password");
			return new ResponseEntity<String>("modifyUserDetails Successfully",HttpStatus.OK);
		}
		else {
			logger.error("The user doesn't exist. Create a new Profile."); 
			throw new UserNotFoundException("The user doesn't exist. Create a new Profile.");
		}
	}


	@Override
	public UserBasic getUserRegistrationdetails(String email) throws UserNotFoundException {
		
		UserBasic u = ubrepo.getUserByUserName(JwtUtil.getTokenUsername());
		if(u.getRole().equals("ROLE_USER")) {
			if(u.getEmail().equals(email)) {
				if (!ubrepo.existsById(email)) {
					logger.error("The User is not found."); 
					throw new UserNotFoundException("The User is not found.");
				}
				
				UserBasic ub= ubrepo.getById(email);
				if(!ub.getUsername().equals(JwtUtil.getTokenUsername())) {
					logger.error("The User is NOT ALLOWED."); 
					throw new UserNotFoundException("The User is NOT ALLOWED.");
				}
				logger.info("getUserRegistrationdetails fetched");
				return ub;
			}
		} else if(u.getRole().equals("ROLE_ADMIN")) {
			if (!ubrepo.existsById(email)) {
				logger.error("The User is not found."); 
				throw new UserNotFoundException("The User is not found.");
			}
			UserBasic ub= ubrepo.getById(email);
			logger.info("getUserRegistrationdetails fetched");
			return ub;
		}
		logger.error("The User is NOT ALLOWED."); 
		throw new UserNotFoundException("The User is NOT ALLOWED.");

	}

	@Override
	public UserAdvanced getUserDetailsService(String email) throws UserNotFoundException {
		
		UserBasic u = ubrepo.getUserByUserName(JwtUtil.getTokenUsername());
		if(u.getRole().equals("ROLE_USER")) {
			if(u.getEmail().equals(email)) {
				if (!ubrepo.existsById(email)) {
					logger.error("The User is not found.");
					throw new UserNotFoundException("The User is not found.");
				}
				
				UserBasic ub= ubrepo.getById(email);
				if(!ub.getUsername().equals(JwtUtil.getTokenUsername())) {
					logger.error("The User is NOT ALLOWED.");
					throw new UserNotFoundException("The User is NOT ALLOWED.");
				}
				logger.info("getUserDetailsService fetched");
				return ubrepo.getById(email).getUserdetails();
			}
		} else if(u.getRole().equals("ROLE_ADMIN")) {
			if (!ubrepo.existsById(email)) {
				logger.error("The User is not found.");
				throw new UserNotFoundException("The User is not found.");
			}
			
			logger.info("getUserDetailsService fetched");
			return ubrepo.getById(email).getUserdetails();
		}
		logger.error("The User is NOT ALLOWED.");
		throw new UserNotFoundException("The User is NOT ALLOWED.");
		
		
	}

	@Override
	public List<LoanAppTable> getAllLoanApplication(String email) throws UserNotFoundException {
		
		UserBasic u = ubrepo.getUserByUserName(JwtUtil.getTokenUsername());
		if(u.getRole().equals("ROLE_USER")) {
			if(u.getEmail().equals(email)) {
				if (!ubrepo.existsById(email)) {
					logger.error("The User is not found.");
					throw new UserNotFoundException("The User is not found.");
				}
				
				UserBasic ub= ubrepo.getById(email);
				if(!ub.getUsername().equals(JwtUtil.getTokenUsername())) {
					logger.error("The User is NOT ALLOWED.");
					throw new UserNotFoundException("The User is NOT ALLOWED.");
				}
				logger.info("getAllLoanApplication fetched");
				return larepo.getAllLoanApplication(email);
			}
		} else if(u.getRole().equals("ROLE_ADMIN")) {
			if (!ubrepo.existsById(email)) {
				logger.error("The User is not found.");
				throw new UserNotFoundException("The User is not found.");
			}
			logger.info("getAllLoanApplication fetched");
			return larepo.getAllLoanApplication(email);
		}
		logger.error("The User is NOT ALLOWED.");
		throw new UserNotFoundException("The User is NOT ALLOWED.");
		

	}

	@Override
	public List<Approved> viewAllApprovedByEmail(String email) throws UserNotFoundException {
		
		
		UserBasic u = ubrepo.getUserByUserName(JwtUtil.getTokenUsername());
		if(u.getRole().equals("ROLE_USER")) {
			if(u.getEmail().equals(email)) {
				if (!ubrepo.existsById(email)) {
					logger.error("The User is not found.");
					throw new UserNotFoundException("The User is not found.");
				}
				
				UserBasic ub= ubrepo.getById(email);
				if(!ub.getUsername().equals(JwtUtil.getTokenUsername())) {
					logger.error("The User is NOT ALLOWED.");
					throw new UserNotFoundException("The User is NOT ALLOWED.");
				}
				logger.info("viewAllApprovedByEmail fetched");
				return aprepo.viewAllApprovedByEmail(email);
			}
		} else if(u.getRole().equals("ROLE_ADMIN")) {
			if (!ubrepo.existsById(email)) {
				logger.error("The User is not found.");
				throw new UserNotFoundException("The User is not found.");
			}
			logger.info("viewAllApprovedByEmail fetched");
			return aprepo.viewAllApprovedByEmail(email);
		}
		logger.error("The User is NOT ALLOWED.");
		throw new UserNotFoundException("The User is NOT ALLOWED.");
		
	
	}

	@Override
	public LoanAppTable getLoanApplicationByChassis(int chassisNo) throws UserNotAllowed {

		
		if(ubrepo.getUserByUserName(JwtUtil.getTokenUsername()).getUserdetails().getUserId() == larepo.getById(chassisNo).getUser().getUserId() || ubrepo.getUserByUserName(JwtUtil.getTokenUsername()).getRole().equals("RULE_ADMIN") ) {
			logger.info("getLoanApplicationByChassis fetched");
			return larepo.getById(chassisNo);
		} else {
			logger.error("USER NOT ALLOWED TO ACCESS THIS RECORD");
			throw new UserNotAllowed("USER NOT ALLOWED TO ACCESS THIS RECORD");
		}
		
	}

	@Override
	public Approved viewApprovedByLoanId(int loanId) throws UserNotAllowed {
		
		
		int chassisNo = aprepo.findById(loanId).get().getLoanapp().getChassisNo();
		if(ubrepo.getUserByUserName(JwtUtil.getTokenUsername()).getUserdetails().getUserId() == larepo.getById(chassisNo).getUser().getUserId() || ubrepo.getUserByUserName(JwtUtil.getTokenUsername()).getRole().equals("RULE_ADMIN") ) {
			logger.info("viewApprovedByLoanId fetched");
			return aprepo.viewApprovedByLoanId(loanId);
		} else {
			logger.error("USER NOT ALLOWED TO ACCESS THIS RECORD");
			throw new UserNotAllowed("USER NOT ALLOWED TO ACCESS THIS RECORD");
		}
	}

	@Override
	public List<LoanAppTable> getAllRejectedByEmail(String email) throws UserNotAllowed {
		
		if(ubrepo.getUserByUserName(JwtUtil.getTokenUsername()).getEmail().equals(email) || ubrepo.getUserByUserName(JwtUtil.getTokenUsername()).getRole().equals("RULE_ADMIN") ) {
			logger.info("getAllRejectedByEmail fetched");
			return larepo.getAllRejectedByEmail(email);
		} else {
			logger.error("USER NOT ALLOWED TO ACCESS THIS RECORD");
			throw new UserNotAllowed("USER NOT ALLOWED TO ACCESS THIS RECORD");
		}
	}

	@Override
	public boolean verifyUserLogin(LoginDto login) throws UserNotFoundException {
		if (!ubrepo.existsById(login.getEmail())) {
			logger.error("The User is not found.");
			throw new UserNotFoundException("The User is not found.");
		}
		
		UserBasic ub = ubrepo.getById(login.getEmail());
		if (ub.getEmail().equals(login.getEmail()) && ub.getPassword().equals(login.getPassword()) && ub.getRole().equals("ROLE_USER")) {
			
			logger.info("verifyUserLogin true");
			return true;
		}
		logger.info("verifyUserLogin false");
		return false;
	}
}
