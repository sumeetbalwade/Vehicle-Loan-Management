package com.capgemini.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capgemini.model.EmiClass;

public interface EMIService {
	public String generateOTP();
	public ResponseEntity<String> sendOTP(String to, String sub, String msg);
	public double EMICalculate(double loanAmount, int termInYears, double interestRate);
	public List<EmiClass> calculateEmi(double loanAmount, int termInYears, double interestRate, Date appdate);
}
