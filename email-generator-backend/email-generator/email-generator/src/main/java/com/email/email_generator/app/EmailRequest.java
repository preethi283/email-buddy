package com.email.email_generator.app;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmailRequest {
	@JsonProperty("emailContent")
	private String emailContent;
	@JsonProperty("tone")
	private String tone;
	

}
