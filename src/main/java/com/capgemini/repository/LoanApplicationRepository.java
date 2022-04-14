package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.LoanAppTable;

public interface LoanApplicationRepository extends JpaRepository<LoanAppTable,Integer>{

}
