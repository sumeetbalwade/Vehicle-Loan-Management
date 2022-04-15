package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Approved;

@Repository
public interface ApprovedRepository extends JpaRepository<Approved,Integer>{
	
	@Query(value="select * from approved_vla where loan_id=?1",nativeQuery=true)
	public Approved viewApprovedByLoanId(int loanId);
	
	@Query(value="select * from approved_vla av,account_vla avl, user_basic_vla ubv where ubv.email=?1 and ubv.userdetails_user_id=avl.user_user_id and avl.account_no=av.account_account_no",nativeQuery=true)
	public List<Approved> viewAllApprovedByEmail(String email);

}
