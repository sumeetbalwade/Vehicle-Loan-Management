package com.capgemini.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.capgemini.model.EmiClass;
import com.capgemini.service.EMIService;

public class EMIDao implements EMIService{

	@Autowired
	private JavaMailSender jms;
	
	@Override
	public String generateOTP() {
		int randomPin = (int) (Math.random()*9000)+1000;
        String otp  = String.valueOf(randomPin);
        return otp;
	}

	@Override
	public void sendOTP(String to, String sub, String msg) {
		String from="balwadesr@gmail.com";
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setTo(to);
		smm.setFrom(from);
		smm.setSubject(sub);
		smm.setText(msg);
		jms.send(smm);
		System.out.println("OTP Sent");
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
