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
	
	@Query(value="select * from loan_app_table_vla latv, user_basic_vla ubv where ubv.email=?1 and ubv.userdetails_user_id=latv.user_user_id and latv.status='Rejected'",nativeQuery=true)
	public List<LoanAppTable> getAllRejectedByEmail(String email);
	
	@Query(value="select * from approved_vla where loan_id=?1",nativeQuery=true)
	public Approved viewApprovedByLoanId(int loanId);
	
	@Query(value="select * from approved_vla av,account_vla avl, user_basic_vla ubv where ubv.email=?1 and ubv.userdetails_user_id=avl.user_user_id and avl.account_no=av.account_account_no",nativeQuery=true)
	public List<Approved> viewAllApprovedByEmail(String email);
	
	@Query(value="select * from loan_app_table_vla where chassis_no=?1",nativeQuery=true)
	public LoanAppTable getLoanApplicationByChassis(String chassisNo);
	
	@Query(value="select * from loan_app_table_vla latv, user_basic_vla ubv where ubv.email=?1 and ubv.userdetails_user_id=latv.user_user_id",nativeQuery=true)
	public List<LoanAppTable> getAllLoanApplication(String email);
	
	//@Query(value="select * from user_advanced_vla uav, user_basic_vla ubv where ubv.email=?1 and ubv.userdetails_user_id=uav.user_id",nativeQuery=true)
	//public UserAdvanced getUserDetailsService(String email);
	
	

}
