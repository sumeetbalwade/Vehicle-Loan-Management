package com.capgemini.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="Approved_VLA")
public class Approved  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int loanId;
	
	
	private double emi;
	private LocalDate emidate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JsonBackReference
	private Account account;
	
	@OneToOne(cascade=CascadeType.ALL)
	private LoanAppTable loanapp;

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

	public LocalDate getEmidate() {
		return emidate;
	}

	public void setEmidate(LocalDate emidate) {
		this.emidate = emidate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LoanAppTable getLoanapp() {
		return loanapp;
	}

	public void setLoanapp(LoanAppTable loanapp) {
		this.loanapp = loanapp;
	}
	
	
}
