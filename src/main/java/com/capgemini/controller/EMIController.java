package com.capgemini.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dao.EMIDao;
import com.capgemini.model.EmiClass;
import com.capgemini.model.OtpTemplate;
import com.capgemini.repository.EMIRepository;

@RestController
public class EMIController {
	
	@Autowired
	EMIDao emidao;
	
	@Autowired
	EMIRepository emirepo;
	
	@GetMapping(path="/generateOTP")
	public String generateOTP() {
		return emidao.generateOTP();
	}
	
	@PutMapping(path="/sendOTP")
	public ResponseEntity<String> sendOTP(@RequestBody OtpTemplate otpTemplate) {
		return emidao.sendOTP(otpTemplate.to, otpTemplate.subject, otpTemplate.messgage);
	}
	
	@PostMapping(path="/admin/EMICalculate/{loanAmount}/{termInYears}/{interestRate}")
	public EmiClass EMICalculate(@PathVariable double loanAmount,@PathVariable int termInYears,@PathVariable double interestRate) {
		double emi=emidao.EMICalculate(loanAmount, termInYears, interestRate);
		return emirepo.save(new EmiClass(LocalDate.now(),loanAmount+emi,emi,loanAmount,termInYears,loanAmount+emi,"APPROVED"));	
	}
	
	@GetMapping(path="/admin/calculateEmi")
	public List<EmiClass> calculateEmi(double loanAmount, int termInYears, double interestRate, Date appdate){
		return emidao.calculateEmi(loanAmount, termInYears, interestRate, appdate);
	}

}
