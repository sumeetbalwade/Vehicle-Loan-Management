package com.capgemini.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="LoanAppTable_VLA")
public class LoanAppTable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int chassisNo;
	
	@NotNull(message="Existing EMI shouldn't be blank")
	private double existingEMI;
	@NotNull(message="Tenure shouldn't be blank")
	private int tenure;
	@NotNull(message="Interest shouldn't be blank")
	private double interest;
	@NotNull(message="Amount shouldn't be blank")
	private double amount;
	
	@Value("${my.date}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate appdate;
	private String status="PENDING";
	
	@NotBlank(message="Brand shouldn't be left empty.")
	private String brand;
	@NotBlank(message="Color shouldn't be left empty.")
	private String colour;
	@NotBlank(message="Model shouldn't be left empty.")
	private String model;
	@NotNull(message="Type shouldn't be blank")
	private int type;
	@NotNull(message="Show Room Price shouldn't be blank")
	private double exShowPrice;
	@NotNull(message="On Road Price shouldn't be blank")
	private double onRoadPrice;
	
	@ManyToOne
	private UserAdvanced user;

	public int getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(int chassisNo) {
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
		this.appdate = LocalDate.now();
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
