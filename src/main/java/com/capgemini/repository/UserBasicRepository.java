package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.UserBasic;

@Repository
public interface UserBasicRepository extends JpaRepository<UserBasic,String>{

}
