package com.capgemini.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="Approved_VLA")
public class Approved  {
	
	@Id
	private int loanId;
	private double emi;
	private LocalDate emidate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JsonBackReference
	private Account account;
	
	@OneToOne(cascade=CascadeType.ALL)
	private LoanAppTable loanapp;
}
