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

	
	@Query(value="select * from user_basic_vla ubv, loan_app_table_vla latv where ubv.userdetails_user_id=latv.user_user_id and latv.status='APPROVED'",nativeQuery=true)
	public List<UserBasic> viewAllApprovedUsers();
	
	@Query(value="select * from user_basic_vla ubv, loan_app_table_vla latv where ubv.userdetails_user_id=latv.user_user_id and latv.status='REJECTED'",nativeQuery=true)
	public List<UserBasic> viewAllRejectedUsers();
	
	@Query(value="select * from user_basic_vla ubv, loan_app_table_vla latv where ubv.userdetails_user_id=latv.user_user_id and latv.status='PENDING'",nativeQuery=true)
	public List<UserBasic> viewAllPendingUsers();
	
	@Query(value="select * from user_basic_vla ubv where ubv.userdetails_user_id=:userid",nativeQuery=true)
	public UserBasic getUserByUserId(int userid);
	
	@Query(value="select * from user_basic_vla ubv where ubv.username=:username",nativeQuery=true)
	public UserBasic getUserByUserName(String username);
	

	//@Query(value="select * from user_advanced_vla uav, user_basic_vla ubv where ubv.email=?1 and ubv.userdetails_user_id=uav.user_id",nativeQuery=true)
	//public UserAdvanced getUserDetailsService(String email);
	public UserBasic findByUsername(String username);
	

}
