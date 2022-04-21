package com.capgemini.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.capgemini.controller.AdminController;
import com.capgemini.controller.EMIController;
import com.capgemini.model.EmiClass;
import com.capgemini.repository.EMIRepository;
import com.capgemini.service.EMIService;

@Service
public class EMIDao implements EMIService{

	@Autowired
	private JavaMailSender jms;
	
	@Autowired
	EMIRepository emirepo;
	Logger logger = LoggerFactory.getLogger(EMIController.class);
	
	@Override
	public String generateOTP() {
		int randomPin = (int) (Math.random()*9000)+1000;
        String otp  = String.valueOf(randomPin);
        logger.info("generateOTP sucessful");
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
		logger.info("OTP Sent sucessful");
		System.out.println("OTP Sent");
	}

	@Override
	public double EMICalculate(double loanAmount, int termInYears, double interestRate) {
		//double E= loanAmount*interestRate[ ((1+interestRate)^n)/ (((1+interestRate)^n)-1)] ;
		logger.info("Emi Calculated");
		return (double) loanAmount*interestRate*((Math.pow((1+interestRate),(termInYears*12))/ (Math.pow((1+interestRate),(termInYears*12))-1)));
	}

	@Override
	public List<EmiClass> calculateEmi(double loanAmount, int termInYears, double interestRate, Date appdate) {
		logger.info("Emi Calculated");
		return emirepo.calculateEmi(appdate);
	}

}
