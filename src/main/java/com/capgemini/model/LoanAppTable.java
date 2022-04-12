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

@Data
@NoArgsConstructor
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
	
	@OneToOne(cascade=CascadeType.ALL)
	private Approved approved;
}
