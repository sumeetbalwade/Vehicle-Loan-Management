package com.capgemini;

import java.time.LocalDateTime;

public class ErrorMessage {
	private LocalDateTime date;
	private String errorMsg;

	public ErrorMessage(LocalDateTime date, String errorMsg) {
		super();
		this.date = date;
		this.errorMsg = errorMsg;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	

}
