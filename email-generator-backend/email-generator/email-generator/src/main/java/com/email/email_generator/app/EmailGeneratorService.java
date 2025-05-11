package com.email.email_generator.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//import lombok.Value;

@Service
public class EmailGeneratorService {
	
	@Value("${gemini.api.url}")
	private String geminiAPIUrl;
	@Value("${gemini.api.key}")
	private String geminiAPIKey;
	private WebClient webClient;
	
	public EmailGeneratorService(WebClient.Builder webClientBuilder) {
		this.webClient=webClientBuilder.build();
	}
	
    public String generateEmailReply(EmailRequest emailRequest) {
    	//Build the prompt
    	String prompt=buildPrompt(emailRequest);	
    	
    	//craft a request
    	Map<String,Object> requestBody=Map.of(
    			"contents",new Object[] {
    	       Map.of("parts",new Object[] {
    	    		   Map.of("text",prompt)
    	       })
    	}
    	);
    	//Do request and get response
    	System.out.println("endpoint8888*********"+geminiAPIUrl+geminiAPIKey);
    	String response=webClient.post()
    			
    			.uri(geminiAPIUrl+geminiAPIKey)
    			
    			.header("Content-Type","application/json")
    			.bodyValue(requestBody)
    			.retrieve()
    			.bodyToMono(String.class)
    			.block();
    	
    	//return response
    	
		return extractResponseContent(response);
    	
    }

	private String extractResponseContent(String response) {
		
		try {
			ObjectMapper mapper=new ObjectMapper();//tool from jackson library, helps with json data . convert json data to java objs and vice versa.
			JsonNode rootNode=mapper.readTree(response);	// making use of json node. readTree converts json response to tree like structure.
			return rootNode.path("candidates")
					.get(0)
					.path("content")
					.path("parts")
					.get(0)
					.path("text")
					.asText();
					
					
			
		}catch(Exception e){
			return "Error processing request:"+e.getMessage();
		}
	}

	private String buildPrompt(EmailRequest emailRequest) {
		StringBuilder prompt=new StringBuilder();
		prompt.append("Generate a professional email reply for the following email content.please dont generate the subject line ");
		if(emailRequest.getTone()!=null && !emailRequest.getTone().isEmpty()) {
			prompt.append("use a  ").append(emailRequest.getTone()).append(" tone");
		}
		prompt.append("\n original email:\n").append(emailRequest.getEmailContent());
		return prompt.toString();
	}
}
