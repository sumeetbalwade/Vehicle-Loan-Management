package com.capgemini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import com.capgemini.dao.EMIDao;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class VehicleLoanAppDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(VehicleLoanAppDemoApplication.class, args);
		
	}
	
	/*
	@EventListener( ApplicationReadyEvent   )
	public void sendMail() {
		emdao.sendOTP("balwadesr@gmail.com", "Test Run ", "ALL THE BEST!!");
	}
	*/
	

}
