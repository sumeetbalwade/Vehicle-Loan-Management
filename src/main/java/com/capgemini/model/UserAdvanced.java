package com.capgemini.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="UserAdvanced_VLA")
public class UserAdvanced  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@NotBlank(message="Address shouldn't be left empty.")
	private String address;
	@NotBlank(message="State shouldn't be left empty.")
	private String state;
	@NotBlank(message="City shouldn't be left empty.")
	private String city;
	@NotBlank(message="Pin shouldn't be left empty.")
	private String pin;
	@NotBlank(message="Employee Type shouldn't be left empty.")
	private String emptype;
	@NotNull(message="Salary shouldn't be blank")
	private double salary;
	@NotBlank(message="Aadhar details shouldn't be left empty.")
	private String aadhar;
	@NotBlank(message="PAN details shouldn't be left empty.")
	private String pan;
	@NotBlank(message="Salary Slip details shouldn't be left empty.")
	private String salarySlip;
	@NotBlank(message="Address Proof shouldn't be left empty.")
	private String addressProof;
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getEmptype() {
		return emptype;
	}

	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getSalarySlip() {
		return salarySlip;
	}

	public void setSalarySlip(String salarySlip) {
		this.salarySlip = salarySlip;
	}

	public String getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}

	@Override
	public String toString() {
		return "UserAdvanced [userId=" + userId + ", address=" + address + ", state=" + state + ", city=" + city
				+ ", pin=" + pin + ", emptype=" + emptype + ", salary=" + salary + ", aadhar=" + aadhar + ", pan=" + pan
				+ ", salarySlip=" + salarySlip + ", addressProof=" + addressProof + "]";
	}
	
	
}
