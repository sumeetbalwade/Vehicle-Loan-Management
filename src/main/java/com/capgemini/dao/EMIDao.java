package com.capgemini.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.model.EmiClass;
import com.capgemini.service.EMIService;

public class EMIDao implements EMIService{

	@Override
	public String generateOTP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendOTP(String to, String sub, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double EMICalculate(double loanAmount, int termInYears, double interestRate) {
		//double E= loanAmount*interestRate[ ((1+interestRate)^n)/ (((1+interestRate)^n)-1)] ;
		return 0;
	}

	@Override
	public List<EmiClass> calculateEmi(double loanAmount, int termInYears, double interestRate, Date appdate) {
		
		return null;
	}

}
