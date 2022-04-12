package com.capgemini.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="UserBasic_VLA")
public class UserBasic {
	
	@Id
	private String email;
	
	private String name;
	private String gender;
	private String mobile;
	private int age;
	private String password;
	
	@OneToOne(cascade=CascadeType.ALL)
	private UserAdvanced userdetails;

}
