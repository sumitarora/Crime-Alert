package com.crimealert.model;

import lombok.Data;

@Data
public class Email {

	private String to;
	private String from;
	private String subject;
	private String  content;
	private String status;
	
}
