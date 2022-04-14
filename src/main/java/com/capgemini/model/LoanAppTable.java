package com.capgemini.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="LoanAppTable_VLA")
public class LoanAppTable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String chassisNo;
	
	private double existingEMI;
	private int tenure;
	private double interest;
	private double amount;
	private LocalDate appdate;
	private String status="PENDING";
	private String brand;
	private String colour;
	private String model;
	private int type;
	private double exShowPrice;
	private double onRoadPrice;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private UserAdvanced user;

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public double getExistingEMI() {
		return existingEMI;
	}

	public void setExistingEMI(double existingEMI) {
		this.existingEMI = existingEMI;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getAppdate() {
		return appdate;
	}

	public void setAppdate(LocalDate appdate) {
		this.appdate = appdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getExShowPrice() {
		return exShowPrice;
	}

	public void setExShowPrice(double exShowPrice) {
		this.exShowPrice = exShowPrice;
	}

	public double getOnRoadPrice() {
		return onRoadPrice;
	}

	public void setOnRoadPrice(double onRoadPrice) {
		this.onRoadPrice = onRoadPrice;
	}

	public UserAdvanced getUser() {
		return user;
	}

	public void setUser(UserAdvanced user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
