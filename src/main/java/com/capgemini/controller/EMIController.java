package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.capgemini.dao.EMIDao;

public class EMIController {
	
	@Autowired
	EMIDao emidao;
	
	@GetMapping(path="/generateOTP")
	public String generateOTP() {
		return emidao.generateOTP();
	}
	
	@PutMapping(path="/sendOTP/{to}/{sub}/{msg}")
	public void sendOTP(@PathVariable String to,@PathVariable String sub,@PathVariable String msg) {
		emidao.sendOTP(to, sub, msg);
	}
	

}
