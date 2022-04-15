package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.model.LoanAppTable;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanAppTable,Integer>{
	
	@Query(value="select * from loan_app_table_vla latv, user_basic_vla ubv where ubv.email like ?1 and ubv.userdetails_user_id=latv.user_user_id",nativeQuery=true)
	public List<LoanAppTable> getAllLoanApplication(String email);
	
	@Query(value="select * from loan_app_table_vla latv, user_basic_vla ubv where ubv.email=?1 and ubv.userdetails_user_id=latv.user_user_id and latv.status='Rejected'",nativeQuery=true)
	public List<LoanAppTable> getAllRejectedByEmail(String email);
	
	//@Query(value="select * from loan_app_table_vla where chassis_no=?1",nativeQuery=true)
	//public LoanAppTable getLoanApplicationByChassis(String chassisNo);

}
