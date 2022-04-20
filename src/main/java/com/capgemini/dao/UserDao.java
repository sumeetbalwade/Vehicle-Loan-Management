package com.capgemini.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exception.UserAlreadyExistsException;
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
	public void UserRegisterService(UserBasic userbasic) throws UserAlreadyExistsException {
		if (ubrepo.existsById(userbasic.getEmail())) {
			throw new UserAlreadyExistsException("The User already exists.");
		}
		if(isValidPassword(userbasic.getPassword())) {
			
			ubrepo.save(userbasic);
		}else {
			throw new UserAlreadyExistsException("Passsword is not valid");
		}

	}

	@Override
	public void applyVehicleLoan(LoanUserHolder loanuserholder) throws UserNotFoundException {
		loanuserholder.lat.setAppdate(LocalDate.now());
		System.out.println("DAO");
		larepo.save(loanuserholder.lat);
		System.out.println("DAO1");
		if(!loanuserholder.ub.getUsername().equals(JwtUtil.getTokenUsername())) {
			throw new UserNotFoundException("The User is NOT ALLOWED.");
		}
		if (ubrepo.existsById(loanuserholder.ub.getEmail())) {
		}
		else ubrepo.save(loanuserholder.ub);
		System.out.println("DAO2");
	}

	@Override
	public void resetPasswordService(UserBasic userbasic) throws UserNotFoundException {
		if (!ubrepo.existsById(userbasic.getEmail())) {
			throw new UserNotFoundException("The User is not found.");
		}
		
		if(!userbasic.getUsername().equals(JwtUtil.getTokenUsername())) {
			throw new UserNotFoundException("The User is NOT ALLOWED.");
		}
		UserBasic ub = ubrepo.getById(userbasic.getEmail());
		ub.setPassword(userbasic.getPassword());
		ubrepo.save(ub);
	}

	@Override
	public void modifyUserDetails(UserAdvanced user) throws UserNotFoundException {
		if (uadrepo.existsById(user.getUserId())) {
			
			if(!ubrepo.getUserByUserId(user.getUserId()).getUsername().equals(JwtUtil.getTokenUsername())) {
				throw new UserNotFoundException("The User is NOT ALLOWED.");
			}
			uadrepo.save(user);
		}
		else throw new UserNotFoundException("The user doesn't exist. Create a new Profile.");
	}


	@Override
	public UserBasic getUserRegistrationdetails(String email) throws UserNotFoundException {
		
		UserBasic u = ubrepo.getUserByUserName(JwtUtil.getTokenUsername());
		if(u.getRole().equals("ROLE_USER")) {
			if(u.getEmail().equals(email)) {
				if (!ubrepo.existsById(email)) {
					throw new UserNotFoundException("The User is not found.");
				}
				
				UserBasic ub= ubrepo.getById(email);
				if(!ub.getUsername().equals(JwtUtil.getTokenUsername())) {
					throw new UserNotFoundException("The User is NOT ALLOWED.");
				}
				return ub;
			}
		} else if(u.getRole().equals("ROLE_ADMIN")) {
			if (!ubrepo.existsById(email)) {
				throw new UserNotFoundException("The User is not found.");
			}
			UserBasic ub= ubrepo.getById(email);
			return ub;
		}
		throw new UserNotFoundException("The User is NOT ALLOWED.");

	}

	@Override
	public UserAdvanced getUserDetailsService(String email) throws UserNotFoundException {
		
		UserBasic u = ubrepo.getUserByUserName(JwtUtil.getTokenUsername());
		if(u.getRole().equals("ROLE_USER")) {
			if(u.getEmail().equals(email)) {
				if (!ubrepo.existsById(email)) {
					throw new UserNotFoundException("The User is not found.");
				}
				
				UserBasic ub= ubrepo.getById(email);
				if(!ub.getUsername().equals(JwtUtil.getTokenUsername())) {
					throw new UserNotFoundException("The User is NOT ALLOWED.");
				}
				return ubrepo.getById(email).getUserdetails();
			}
		} else if(u.getRole().equals("ROLE_ADMIN")) {
			if (!ubrepo.existsById(email)) {
				throw new UserNotFoundException("The User is not found.");
			}
			return ubrepo.getById(email).getUserdetails();
		}
		throw new UserNotFoundException("The User is NOT ALLOWED.");
		
		
	}

	@Override
	public List<LoanAppTable> getAllLoanApplication(String email) throws UserNotFoundException {
		
		UserBasic u = ubrepo.getUserByUserName(JwtUtil.getTokenUsername());
		if(u.getRole().equals("ROLE_USER")) {
			if(u.getEmail().equals(email)) {
				if (!ubrepo.existsById(email)) {
					throw new UserNotFoundException("The User is not found.");
				}
				
				UserBasic ub= ubrepo.getById(email);
				if(!ub.getUsername().equals(JwtUtil.getTokenUsername())) {
					throw new UserNotFoundException("The User is NOT ALLOWED.");
				}
				return larepo.getAllLoanApplication(email);
			}
		} else if(u.getRole().equals("ROLE_ADMIN")) {
			if (!ubrepo.existsById(email)) {
				throw new UserNotFoundException("The User is not found.");
			}
			return larepo.getAllLoanApplication(email);
		}
		throw new UserNotFoundException("The User is NOT ALLOWED.");
		

	}

	@Override
	public List<Approved> viewAllApprovedByEmail(String email) {
		return aprepo.viewAllApprovedByEmail(email);
	}

	@Override
	public LoanAppTable getLoanApplicationByChassis(int chassisNo) {
		return larepo.getById(chassisNo);
	}

	@Override
	public Approved viewApprovedByLoanId(int loanId) {
		return aprepo.viewApprovedByLoanId(loanId);
	}

	@Override
	public List<LoanAppTable> getAllRejectedByEmail(String email) {
		return larepo.getAllRejectedByEmail(email);
	}

	@Override
	public boolean verifyUserLogin(LoginDto login) throws UserNotFoundException {
		if (!ubrepo.existsById(login.getEmail())) {
			throw new UserNotFoundException("The User is not found.");
		}
		UserBasic ub = ubrepo.getById(login.getEmail());
		if (ub.getEmail().equals(login.getEmail()) && ub.getPassword().equals(login.getPassword()))
			return true;
		return false;
	}
}
