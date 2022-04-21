package com.capgemini.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.AdminDao;
import com.capgemini.dao.UserDao;
import com.capgemini.exception.AdminNotFoundException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.Account;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.LoginDto;
import com.capgemini.model.UserAdvanced;
import com.capgemini.model.UserBasic;
import com.capgemini.repository.UserAdvancedRepository;
import com.capgemini.repository.UserBasicRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class VehicleLoanAppDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserDao ud;
	
	
	@Autowired
	private AdminDao ad;
	
	
	@Autowired
	UserBasicRepository ubrepo;
	
	@Test
	public void testGetUserRegistrationdetails() {
		
		
		
//		UserAdvanced userAdvanced = new UserAdvanced(11,"Wagh","MH","pune","412207","emp",300000.0,"888888888888","DVRPB4239R","Sall","Pune",new Account());
//		userAdvanced.getAccount().setAccountNo(11);
//		String email = "admin11@gmail.com";
//		when(ubrepo.getById(email).getUserdetails()).thenReturn(userAdvanced);
//		try {
//			assertEquals(userAdvanced, ud.testGetUserRegistrationdetails(email));
//		} catch (UserNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void verifyAdminLoginTest() {
//		try {
//			when(ad.verifyAdminLogin(new LoginDto("admin@gmail.com", "Sumeet@26"))).thenReturn(true);
//		} catch (AdminNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			assertEquals(true, ad.verifyAdminLogin(new LoginDto("admin@gmail.com", "Sumeet@26")));
//		} catch (AdminNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
