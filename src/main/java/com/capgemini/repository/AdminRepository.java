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
	
}
