package com.capgemini.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="Account_VLA")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long accountNo;
	
	@OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private UserAdvanced user;
	
	@OneToMany(mappedBy="account",cascade=CascadeType.ALL)//targetEntity=Account.class
	@JsonManagedReference
	private Set<Approved> loans;

}
