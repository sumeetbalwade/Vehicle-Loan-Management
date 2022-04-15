package com.capgemini.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.model.EmiClass;

@Repository
public interface EMIRepository extends JpaRepository<EmiClass,Integer>{
	
	@Query(value="select * from emi_class_vla ecv where ecv.date=?1",nativeQuery=true)
	public List<EmiClass> calculateEmi(Date appdate);
	

}
