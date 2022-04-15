package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Account;
import com.capgemini.model.AdminDetails;
import com.capgemini.model.LoanAppTable;
import com.capgemini.model.UserBasic;

@Repository
public interface AdminRepository extends JpaRepository<AdminDetails,String>{
	
	@Query(value="update loan_app_table_vla set status=?1 where chassis_no=?2",nativeQuery=true)
	public void modifyStatus(String status,int i);
	
	@Query(value="select * from account_vla av,user_basic_vla ubv where ubv.userdetails_user_id=av.user_user_id and ubv.email=?1",nativeQuery=true)
	public Account getAccountByEmailService(String email);
	
	@Query(value="select * from loan_app_table_vla where status='Accepted'",nativeQuery=true)
	public List<LoanAppTable> viewAllAcceptedLoanApplications();
	
	@Query(value="select * from loan_app_table_vla where status='Rejected'",nativeQuery=true)
	public List<LoanAppTable> viewAllRejectedLoanApplications();
	
	@Query(value="select * from user_basic_vla ubv, loan_app_table_vla latv where ubv.userdetails_user_id=latv.user_user_id and latv.status='APPROVED'",nativeQuery=true)
	public List<UserBasic> viewAllApprovedUsers();
	
	@Query(value="select * from user_basic_vla ubv, loan_app_table_vla latv where ubv.userdetails_user_id=latv.user_user_id and latv.status='REJECTED'",nativeQuery=true)
	public List<UserBasic> viewAllRejectedUsers();
	
	@Query(value="select * from user_basic_vla ubv, loan_app_table_vla latv where ubv.userdetails_user_id=latv.user_user_id and latv.status='PENDING'",nativeQuery=true)
	public List<UserBasic> viewAllPendingUsers();
	
}
