package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Approved;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.UserAdvanced;
import com.capgemini.model.UserBasic;

@Repository
public interface UserBasicRepository extends JpaRepository<UserBasic,String>{

	
	//@Query(value="select * from user_advanced_vla uav, user_basic_vla ubv where ubv.email=?1 and ubv.userdetails_user_id=uav.user_id",nativeQuery=true)
	//public UserAdvanced getUserDetailsService(String email);
	
	

}
