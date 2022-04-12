package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.UserAdvanced;

@Repository
public interface UserAdvancedRepository  extends JpaRepository<UserAdvanced,Integer>{

}
