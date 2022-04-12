package com.capgemini.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDto {
	
	
	private String email;
	private String name;
	private String gender;
	private String mobile;
	private int age;

}
