package com.capgemini.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="EmiClass_VLA")
public class EmiClass {
	
	
	private LocalDate date;
	private String beginningBalance;
	private String emi;
	private String principal;
	private String interest;
	private String endingBalance;
	private String status;
	
	
	
}
