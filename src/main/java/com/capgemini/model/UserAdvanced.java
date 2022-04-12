package com.capgemini.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="UserAdvanced_VLA")
public class UserAdvanced  {
	
	@Id
	private int userId;
	
	private String address;
	private String state;
	private String city;
	private String pin;
	private String emptype;
	private double salary;
	private String aadhar;
	private String pan;
	private String salarySlip;
	private String addressProof;
	
	@OneToOne(cascade=CascadeType.ALL)
	private UserBasic userregister;
	
	@OneToMany(mappedBy="user")
	private Set<LoanAppTable> loanapp;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Account account;

}
