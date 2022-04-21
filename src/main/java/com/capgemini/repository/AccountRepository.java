package com.capgemini.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.model.Account;
import com.capgemini.model.AdminDetails;

public interface AccountRepository extends JpaRepository<Account,Long>{
	
	@Query(value="select * from account_vla av,user_basic_vla ubv where ubv.userdetails_user_id=av.user_user_id and ubv.email=?1",nativeQuery=true)
	public Account getAccountByEmailService(String email);

}
