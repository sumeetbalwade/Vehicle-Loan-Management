
package com.capgemini.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="EmiClass_VLA")
public class EmiClass {
	
	@Id
	private int emiid;
	
	private LocalDate date;
	private double beginningBalance;
	private double emi;
	private double principal;
	private double interest;
	private double endingBalance;
	private String status;
	
	public int getEmiid() {
		return emiid;
	}
	public void setEmiid(int emiid) {
		this.emiid = emiid;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getBeginningBalance() {
		return beginningBalance;
	}
	public void setBeginningBalance(double beginningBalance) {
		this.beginningBalance = beginningBalance;
	}
	public double getEmi() {
		return emi;
	}
	public void setEmi(double emi) {
		this.emi = emi;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getEndingBalance() {
		return endingBalance;
	}
	public void setEndingBalance(double endingBalance) {
		this.endingBalance = endingBalance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
