package com.capgemini.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="UserBasic_VLA")
public class UserBasic {
	
	@Id
	@Email
	private String email;
//	@UniqueElements
	@Column(unique = true)
	private String username;
	@JsonIgnore
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@NotBlank(message="Name shouldn't be left empty.")
	private String name;
	
	@NotBlank(message="Gender shouldn't be left empty.")
	private String gender;
	
	@NotBlank(message="Mobile Number shouldn't be left empty.")
	@Pattern(regexp="^[6-9]{1}[0-9]{9}$", message="Mobile number is not valid")
	private String mobile;
	
	@NotNull(message="Age shouldn't be blank")
	@DecimalMin(value="20",message="minimum age should be 20")
	private int age;
	
	
	private String password;

	@OneToOne(cascade=CascadeType.ALL,fetch= FetchType.EAGER)	
	private UserAdvanced userdetails;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAdvanced getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(UserAdvanced userdetails) {
		this.userdetails = userdetails;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	

}
