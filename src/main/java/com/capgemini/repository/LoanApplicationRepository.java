package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.LoanAppTable;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanAppTable,Integer>{

}
