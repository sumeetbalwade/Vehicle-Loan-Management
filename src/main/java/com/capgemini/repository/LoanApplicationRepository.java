package com.capgemini.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.model.LoanAppTable;
import com.capgemini.model.Status;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanAppTable,Integer>{
	
	@Query(value="select * from loan_app_table_vla latv, user_basic_vla ubv where ubv.email like ?1 and ubv.userdetails_user_id=latv.user_user_id",nativeQuery=true)
	public List<LoanAppTable> getAllLoanApplication(String email);
	
	@Query(value="select * from loan_app_table_vla latv, user_basic_vla ubv where ubv.email=?1 and ubv.userdetails_user_id=latv.user_user_id and latv.status='Rejected'",nativeQuery=true)
	public List<LoanAppTable> getAllRejectedByEmail(String email);
	
	//@Query(value="update loan_app_table_vla set status=?1 where chassis_no=?2",nativeQuery=true)
	//public void modifyStatus(Status status,int i);
	
	@Query(value="select * from loan_app_table_vla where status='ACCEPTED'",nativeQuery=true)
	public List<LoanAppTable> viewAllAcceptedLoanApplications();
	
	@Query(value="select * from loan_app_table_vla where status='Rejected'",nativeQuery=true)
	public List<LoanAppTable> viewAllRejectedLoanApplications();
	
	//@Query(value="select * from loan_app_table_vla where chassis_no=?1",nativeQuery=true)
	//public LoanAppTable getLoanApplicationByChassis(String chassisNo);

}
